package com.cmc.mapper;

import com.cmc.model.Clazz;
import com.cmc.model.dto.ClazzDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Clazz mapper
 */
@Mapper
public interface ClazzMapper {
    ClazzMapper INSTANCE = Mappers.getMapper(ClazzMapper.class);

    ClazzDto fromEntity(Clazz clazz);
}
