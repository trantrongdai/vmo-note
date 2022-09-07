package com.vmo.note.service;

import com.vmo.note.dto.request.NoteRequestDto;
import com.vmo.note.dto.request.filter.NoteFilterRequest;
import com.vmo.note.model.BasicNote;
import com.vmo.note.model.dto.NoteDto;
import org.springframework.data.domain.Page;

/**
 * Student serivce interface
 */
public interface BasicNoteService extends NoteConverterStrategy<BasicNote> {
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
     * @param filterRequest
     * @return Page<Note>
     */
    Page<BasicNote> findAll(Integer pageIndex, Integer pageSize, NoteFilterRequest filterRequest);

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
