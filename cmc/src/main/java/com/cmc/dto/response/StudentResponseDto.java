package com.cmc.dto.response;

import com.cmc.model.dto.StudentDto;

/**
 * Student response dto
 */
public class StudentResponseDto {
    StudentDto studentDto;

    public StudentResponseDto() {
    }

    public StudentResponseDto(StudentDto studentDto) {
        this.studentDto = studentDto;
    }

    public StudentDto getStudentDto() {
        return studentDto;
    }

    public void setStudentDto(StudentDto studentDto) {
        this.studentDto = studentDto;
    }
}
