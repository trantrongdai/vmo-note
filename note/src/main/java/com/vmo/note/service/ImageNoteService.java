package com.vmo.note.service;

import com.vmo.note.dto.request.NoteRequestDto;
import com.vmo.note.model.ImageNote;
import com.vmo.note.model.dto.NoteDto;

/**
 * Student serivce interface
 */
public interface ImageNoteService extends NoteConverterStrategy<ImageNote> {
    /**
     * Create note
     *
     * @param noteRequestDto
     * @return
     */
    NoteDto createNote(NoteRequestDto noteRequestDto);

    /**
     * Update note
     *
     * @param id
     * @param noteRequestDto
     * @return
     */
    NoteDto updateNote(Long id, NoteRequestDto noteRequestDto);

    /**
     * Get note detail by id
     *
     * @param id
     * @return NoteDto instance
     */
    NoteDto getNoteDetails(Long id);
}
