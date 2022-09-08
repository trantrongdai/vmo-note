package com.vmo.note.service;

import com.vmo.note.dto.request.UserRequestDto;
import com.vmo.note.model.User;
import com.vmo.note.model.dto.UserDto;

/**
 * User service
 */
public interface UserService {
    /**
     * Create user
     *
     * @param createdUserReq
     * @return
     */
    UserDto createUser(UserRequestDto createdUserReq);

    /**
     * get logged in User
     *
     * @return
     */
    User getLoggedInUser();

    /**
     * get logged in UserDto
     *
     * @return
     */
    UserDto getLoggedInUserDto();

}
