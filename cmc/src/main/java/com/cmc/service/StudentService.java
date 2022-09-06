package com.cmc.service;

import com.cmc.dto.request.BatchDeleteRequestDto;
import com.cmc.dto.request.StudentRequestDto;
import com.cmc.dto.request.filter.StudentFilterRequest;
import com.cmc.model.Student;
import com.cmc.model.dto.StudentDto;
import org.springframework.data.domain.Page;

/**
 * Student serivce interface
 */
public interface StudentService {
    /**
     * Create student
     *
     * @param createRequestDto
     * @return StudentDto instance
     */
    StudentDto createStudent(StudentRequestDto createRequestDto);

    /**
     * Update student
     *
     * @param id
     * @param updateRequestDto
     * @return StudentDto instance
     */
    StudentDto updateStudent(Long id, StudentRequestDto updateRequestDto);

    /**
     * Delete student
     *
     * @param id
     */
    void deleteStudent(Long id);

    /**
     * Get student detail by id
     *
     * @param id
     * @return StudentDto instance
     */
    StudentDto getStudentDetails(Long id);

    /**
     * List Student with paging
     *
     * @param pageIndex
     * @param pageSize
     * @param filterRequest
     * @return Page<Student>
     */
    Page<Student> findAll(Integer pageIndex, Integer pageSize, StudentFilterRequest filterRequest);

    /**
     * batch delete student
     *
     * @param batchDeleteRequestDto
     */
    void batchDelete(BatchDeleteRequestDto batchDeleteRequestDto);
}
