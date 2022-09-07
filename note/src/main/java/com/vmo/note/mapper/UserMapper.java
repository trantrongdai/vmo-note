package com.vmo.note.mapper;

import com.vmo.note.model.User;
import com.vmo.note.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Clazz mapper
 */
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto fromEntity(User user);
}
