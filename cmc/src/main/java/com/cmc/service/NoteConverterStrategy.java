package com.cmc.service;

import com.cmc.dto.request.NoteRequestDto;
import com.cmc.model.dto.NoteDto;

public interface NoteConverterStrategy<T> {

    public NoteDto fromEntity(T t);

    public T fromRequestDto(NoteRequestDto requestDto);
}
