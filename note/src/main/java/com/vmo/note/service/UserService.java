package com.vmo.note.service;

import com.vmo.note.dto.request.UserRequestDto;
import com.vmo.note.model.User;
import com.vmo.note.model.dto.UserDto;

public interface UserService {
    UserDto createUser(UserRequestDto createdUserReq);

    User getLoggedInUser();

    UserDto getLoggedInUserDto();

}
