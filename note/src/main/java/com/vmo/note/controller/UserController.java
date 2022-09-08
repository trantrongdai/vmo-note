package com.vmo.note.controller;

import com.vmo.note.dto.RestResponse;
import com.vmo.note.dto.request.UserRequestDto;
import com.vmo.note.dto.response.UserResponseDto;
import com.vmo.note.enums.StatusCode;
import com.vmo.note.model.dto.UserDto;
import com.vmo.note.repository.UserRepository;
import com.vmo.note.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * UserController class
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userCreatedReq) {
        UserDto createdUser = userService.createUser(userCreatedReq);
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserDto(createdUser);
        return new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(), userResponseDto).build();
    }

}
