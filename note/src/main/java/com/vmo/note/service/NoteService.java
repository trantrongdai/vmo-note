package com.vmo.note.service;

import com.vmo.note.dto.request.NoteRequestDto;
import com.vmo.note.dto.request.filter.NoteFilterRequest;
import com.vmo.note.dto.response.FilterResponseDto;
import com.vmo.note.model.BasicNote;
import com.vmo.note.model.dto.NoteDto;

/**
 * Student serivce interface
 */
public interface NoteService extends NoteConverterStrategy<BasicNote> {
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
     * Delete note
     *
     * @param id
     */
    void deleteNote(Long id);

    /**
     * Get note detail by id
     *
     * @param id
     * @return NoteDto instance
     */
    NoteDto getNoteDetails(Long id);

    /**
     * List note with paging
     *
     * @param pageIndex
     * @param pageSize
     * @return Page<Note>
     */
    FilterResponseDto<NoteDto> findAll(Integer pageIndex, Integer pageSize, NoteFilterRequest noteFilterRequest);

    /**
     * Checking that logged in user is owner of Note or not
     *
     * @param noteId
     */
    void checkingOwner(Long noteId);

    /**
     * Count all uncompleted note
     */
    int countUncompletedNote();
}
