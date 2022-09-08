package com.vmo.note.mapper;

import com.vmo.note.dto.request.UserRequestDto;
import com.vmo.note.model.User;
import com.vmo.note.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * User mapper
 */
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto fromEntity(User user);

    User fromCreateDto(UserRequestDto createReqDto);
}
