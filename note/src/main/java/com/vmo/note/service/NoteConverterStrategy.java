package com.vmo.note.service;

import com.vmo.note.dto.request.NoteRequestDto;
import com.vmo.note.model.dto.NoteDto;

/**
 * Note converter strategy
 *
 * @param <T>
 */
public interface NoteConverterStrategy<T> {

    public NoteDto fromEntity(T t);

    public T getCreateEntity(NoteRequestDto requestDto);

    public T getUpdateEntity(NoteRequestDto requestDto, Long id);
}
