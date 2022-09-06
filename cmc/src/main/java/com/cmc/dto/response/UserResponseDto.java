package com.cmc.dto.response;

import com.cmc.model.dto.UserDto;

/**
 * User response dto
 */
public class UserResponseDto {
    UserDto userDto;

    public UserResponseDto() {
    }

    public UserResponseDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
