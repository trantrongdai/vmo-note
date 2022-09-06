package com.cmc.controller;

import com.cmc.dto.RestResponse;
import com.cmc.dto.request.BatchDeleteRequestDto;
import com.cmc.dto.request.NoteRequestDto;
import com.cmc.dto.request.StudentRequestDto;
import com.cmc.dto.request.filter.StudentFilterRequest;
import com.cmc.dto.response.FilterResponseDto;
import com.cmc.dto.response.NoteResponseDto;
import com.cmc.dto.response.StudentResponseDto;
import com.cmc.enums.StatusCode;
import com.cmc.mapper.StudentMapper;
import com.cmc.model.Student;
import com.cmc.model.dto.NoteDto;
import com.cmc.model.dto.StudentDto;
import com.cmc.service.NoteService;
import com.cmc.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * Student controller
 */
@RestController
@RequestMapping("/note")
public class NoteController extends RestBaseController {
    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    StudentService studentService;

    @Autowired
    NoteService noteService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<NoteResponseDto> createNote(@Valid @RequestBody NoteRequestDto requestDto) {
        NoteDto createdNote = noteService.createNote(requestDto);
        return new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(),
                new NoteResponseDto(createdNote))
                .build();
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<StudentResponseDto> updateStudent(@PathVariable(value = "id") Long id,
                                                          @Valid @RequestBody StudentRequestDto requestDto) {
        StudentDto updatedStudent = studentService.updateStudent(id, requestDto);
        return new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(),
                new StudentResponseDto(updatedStudent))
                .build();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<Long> deleteStudent(@PathVariable(value = "id") Long id) {
        studentService.deleteStudent(id);
        return new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(), id).build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<StudentResponseDto> getStudentDetails(@PathVariable(value = "id") Long id) {
        StudentDto studentDto = studentService.getStudentDetails(id);
        StudentResponseDto studentResponseDto = new StudentResponseDto(studentDto);
        return new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(),
                studentResponseDto)
                .build();
    }

    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<FilterResponseDto> listAllStudent(@RequestParam Integer page,
                                                          @RequestParam Integer pageSize,
                                                          @RequestBody(required = false) StudentFilterRequest filterRequest) {

        Page<Student> students = studentService.findAll(page, pageSize, filterRequest);

        FilterResponseDto<StudentDto> filterResults = new FilterResponseDto<>();

        filterResults
                .setData(students
                        .getContent()
                        .stream()
                        .map(student -> StudentMapper.INSTANCE.fromEntity(student))
                        .collect(Collectors.toList()));

        filterResults.setPageSize(students.getNumberOfElements());
        filterResults.setTotalElements(students.getTotalElements());
        filterResults.setTotalPages(students.getTotalPages());

        return new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(), filterResults).build();
    }

    @PostMapping(value = "/batch-delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<?> batchDeleteStudent(@Valid @RequestBody BatchDeleteRequestDto batchDeleteRequestDto) {
        studentService.batchDelete(batchDeleteRequestDto);
        return new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(), batchDeleteRequestDto.getIds()).build();
    }

}
