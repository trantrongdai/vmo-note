package com.vmo.note.dto.response;

import com.vmo.note.model.dto.NoteDto;

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
