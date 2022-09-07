package com.vmo.note.service.impl;

import com.vmo.note.constants.MessageCode;
import com.vmo.note.dto.request.UserRequestDto;
import com.vmo.note.exceptions.AppException;
import com.vmo.note.exceptions.BadRequestException;
import com.vmo.note.mapper.UserMapper;
import com.vmo.note.model.Role;
import com.vmo.note.model.User;
import com.vmo.note.model.dto.UserDto;
import com.vmo.note.repository.RoleRepository;
import com.vmo.note.repository.UserRepository;
import com.vmo.note.service.UserService;
import com.vmo.note.util.UserDetailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    MessageTranslator messageTranslator;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDto createUser(UserRequestDto userCreatedReq) {
        validateRequestData(userCreatedReq);

        User user = UserMapper.INSTANCE.fromCreateDto(userCreatedReq);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        List<Long> roleRequestedIds = userCreatedReq
                .getRoleIds();

        List<Role> roles = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roleRequestedIds)) {
            roles = roleRepository
                    .findByIdIn(roleRequestedIds);
        }
        user.setRoles(new HashSet<>(roles));

        try {
            User result = userRepository.save(user);
            return UserMapper.INSTANCE.fromEntity(result);
        } catch (Exception e) {
            logger.error("Exception occur when try to create User {} {}", userCreatedReq.getUsername(), e.getMessage());
            throw new AppException("Exception occur when try to create User", e.getCause());
        }
    }

    private void validateRequestData(UserRequestDto userCreatedReq) {
        if (userRepository.existsByUsername(userCreatedReq.getUsername())) {
            throw new BadRequestException(messageTranslator
                    .toLocale(MessageCode.USER_NAME_HAS_BEEN_TAKEN));
        }

        if (CollectionUtils.isEmpty(userCreatedReq.getRoleIds())) {
            throw new BadRequestException(messageTranslator
                    .toLocale(MessageCode.USER_CREATE_WITHOUT_ROLE_VALID));
        }
    }

    @Override
    public User getLoggedInUser() {
        String username = UserDetailUtils.getLoggedInUserName();
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new BadRequestException(String
                        .format(messageTranslator
                                        .toLocale(MessageCode.USER_NAME_NOT_FOUND)
                                , username)));
    }

    @Override
    public UserDto getLoggedInUserDto() {
        User user = getLoggedInUser();
        return UserMapper.INSTANCE.fromEntity(user);
    }

}
