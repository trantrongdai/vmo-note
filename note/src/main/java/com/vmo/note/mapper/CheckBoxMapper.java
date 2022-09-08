package com.vmo.note.mapper;

import com.vmo.note.model.CheckBox;
import com.vmo.note.model.dto.CheckBoxDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Checkbox mapper
 */
@Mapper
public interface CheckBoxMapper {
    CheckBoxMapper INSTANCE = Mappers.getMapper(CheckBoxMapper.class);

    CheckBoxDto fromEntity(CheckBox checkBox);

    CheckBox fromDto(CheckBoxDto checkBoxDto);
}
