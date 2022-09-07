package com.cmc.mapper;

import com.cmc.dto.request.NoteRequestDto;
import com.cmc.model.BasicNote;
import com.cmc.model.dto.NoteDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Student mapper
 */
@Mapper
public interface BasicNoteMapper {
    BasicNoteMapper INSTANCE = Mappers.getMapper(BasicNoteMapper.class);

    BasicNote fromRequestDto(NoteRequestDto noteRequestDto);

    NoteDto fromEntity(BasicNote basicNote);
}
