package com.cmc.mapper;

import com.cmc.dto.request.NoteRequestDto;
import com.cmc.model.Note;
import com.cmc.model.dto.NoteDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Student mapper
 */
@Mapper
public interface NoteMapper {
    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    Note fromRequestDto(NoteRequestDto noteRequestDto);

    NoteDto fromEntity(Note note);
}
