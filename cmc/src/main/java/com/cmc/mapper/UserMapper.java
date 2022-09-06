package com.cmc.mapper;

import com.cmc.model.User;
import com.cmc.model.dto.UserDto;
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
