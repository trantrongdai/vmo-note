package com.vmo.note.mapper;

import com.vmo.note.dto.request.NoteRequestDto;
import com.vmo.note.model.BasicNote;
import com.vmo.note.model.dto.NoteDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * BasicNote mapper
 */
@Mapper
public interface BasicNoteMapper {
    BasicNoteMapper INSTANCE = Mappers.getMapper(BasicNoteMapper.class);

    BasicNote fromRequestDto(NoteRequestDto noteRequestDto);

    NoteDto fromEntity(BasicNote basicNote);
}
