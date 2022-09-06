package com.cmc.dto.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Student request dto
 */
public class StudentRequestDto {
    @NotNull(message = "Student name is required")
    @Length(max = 100)
    private String name;

    @NotNull(message = "Class id is required")
    private Long clazzId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClazzId() {
        return clazzId;
    }

    public void setClazzId(Long clazzId) {
        this.clazzId = clazzId;
    }
}
