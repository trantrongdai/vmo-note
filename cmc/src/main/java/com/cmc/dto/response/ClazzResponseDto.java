package com.cmc.dto.response;

import com.cmc.model.dto.ClazzDto;

/**
 * Clazz response dto
 */
public class ClazzResponseDto {
    ClazzDto clazzDto;

    public ClazzResponseDto() {
    }

    public ClazzResponseDto(ClazzDto clazzDto) {
        this.clazzDto = clazzDto;
    }

    public ClazzDto getClazzDto() {
        return clazzDto;
    }

    public void setClazzDto(ClazzDto clazzDto) {
        this.clazzDto = clazzDto;
    }
}
