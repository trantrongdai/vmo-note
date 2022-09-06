package com.cmc.dto.response;

import com.cmc.model.dto.NoteDto;

/**
 * Note response dto
 */
public class NoteResponseDto {
    NoteDto noteDto;

    public NoteResponseDto() {
    }

    public NoteResponseDto(NoteDto noteDto) {
        this.noteDto = noteDto;
    }

    public NoteDto getNoteDto() {
        return noteDto;
    }

    public void setNoteDto(NoteDto noteDto) {
        this.noteDto = noteDto;
    }
}
