package com.vmo.note.service;

import com.vmo.note.dto.request.UserRequestDto;
import com.vmo.note.dto.response.UserResponseDto;
import com.vmo.note.model.dto.UserDto;

public interface UserService {
    UserDto createUser(UserRequestDto createdUserReq);
}
