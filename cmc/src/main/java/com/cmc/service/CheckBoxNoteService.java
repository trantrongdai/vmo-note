package com.cmc.service;

import com.cmc.dto.request.NoteRequestDto;
import com.cmc.model.CheckBoxNote;
import com.cmc.model.dto.NoteDto;

/**
 * Student serivce interface
 */
public interface CheckBoxNoteService extends NoteConverterStrategy<CheckBoxNote> {
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
