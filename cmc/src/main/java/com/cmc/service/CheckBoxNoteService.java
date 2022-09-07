package com.cmc.service;

import com.cmc.dto.request.BatchDeleteRequestDto;
import com.cmc.dto.request.NoteRequestDto;
import com.cmc.dto.request.filter.NoteFilterRequest;
import com.cmc.model.BasicNote;
import com.cmc.model.dto.NoteDto;
import org.springframework.data.domain.Page;

/**
 * Student serivce interface
 */
public interface CheckBoxNoteService {
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
     * batch delete note
     *
     * @param batchDeleteRequestDto
     */
    void batchDelete(BatchDeleteRequestDto batchDeleteRequestDto);
}
