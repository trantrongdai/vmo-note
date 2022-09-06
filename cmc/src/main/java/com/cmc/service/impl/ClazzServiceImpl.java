package com.cmc.service.impl;

import com.cmc.constants.MessageCode;
import com.cmc.exceptions.AppException;
import com.cmc.exceptions.ResourceNotFoundException;
import com.cmc.mapper.ClazzMapper;
import com.cmc.model.Clazz;
import com.cmc.model.dto.ClazzDto;
import com.cmc.repository.ClazzRepository;
import com.cmc.service.ClazzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ClazzServiceImpl implements ClazzService {

    private static final Logger logger = LoggerFactory.getLogger(ClazzServiceImpl.class);

    @Autowired
    private MessageTranslator messageTranslator;

    @Autowired
    private ClazzRepository clazzRepository;

    @Transactional(readOnly = true)
    @Override
    public ClazzDto findById(Long id) {
        Clazz clazz = clazzRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String
                .format(messageTranslator
                        .toLocale(MessageCode.CLAZZ_ID_NOT_FOUND), id)));

        return ClazzMapper.INSTANCE.fromEntity(clazz);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClazzDto> listAll() {
        try {
            List<Clazz> clazzes = clazzRepository.findAll();
            if (Objects.isNull(clazzes)) {
                return new ArrayList<>();
            }
            return clazzes
                    .stream()
                    .map(clazz -> ClazzMapper.INSTANCE.fromEntity(clazz))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Exception occur when try to get list all of Clazz {} {}", e.getCause());
            throw new AppException("Exception occur when try to get all of Clazz", e.getCause());
        }
    }
}
