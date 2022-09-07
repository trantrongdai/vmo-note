package com.cmc.controller;

import com.cmc.dto.RestResponse;
import com.cmc.dto.request.NoteRequestDto;
import com.cmc.dto.request.filter.NoteFilterRequest;
import com.cmc.dto.response.FilterResponseDto;
import com.cmc.dto.response.NoteResponseDto;
import com.cmc.enums.NoteType;
import com.cmc.enums.StatusCode;
import com.cmc.mapper.BasicNoteMapper;
import com.cmc.model.BasicNote;
import com.cmc.model.dto.NoteDto;
import com.cmc.service.BasicNoteService;
import com.cmc.service.CheckBoxNoteService;
import com.cmc.service.ImageNoteService;
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
    BasicNoteService basicNoteService;

    @Autowired
    ImageNoteService imageNoteService;

    @Autowired
    CheckBoxNoteService checkBoxNoteService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<NoteResponseDto> createNote(@Valid @RequestBody NoteRequestDto requestDto) {
        NoteDto createdNote = null;
        if (NoteType.BASIC_NOTE.name().equals(requestDto.getNoteType())) {
            createdNote = basicNoteService.createNote(requestDto);
        }
        if (NoteType.IMAGE_NOTE.name().equals(requestDto.getNoteType())) {
            createdNote = imageNoteService.createNote(requestDto);
        }
        if (NoteType.CHECKBOX_NOTE.name().equals(requestDto.getNoteType())) {
            createdNote = checkBoxNoteService.createNote(requestDto);
        }
        return new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(),
                new NoteResponseDto(createdNote))
                .build();
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<NoteResponseDto> updateNote(@PathVariable(value = "id") Long id,
                                                       @Valid @RequestBody NoteRequestDto requestDto) {
        NoteDto updatedNote = null;
        if (NoteType.BASIC_NOTE.name().equals(requestDto.getNoteType())) {
            updatedNote = basicNoteService.updateNote(id, requestDto);
        }
        if (NoteType.IMAGE_NOTE.name().equals(requestDto.getNoteType())) {
            updatedNote = imageNoteService.updateNote(id, requestDto);
        }
        if (NoteType.CHECKBOX_NOTE.name().equals(requestDto.getNoteType())) {
            updatedNote = checkBoxNoteService.updateNote(id, requestDto);
        }
        return new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(),
                new NoteResponseDto(updatedNote))
                .build();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<Long> deleteNote(@PathVariable(value = "id") Long id) {
        basicNoteService.deleteNote(id);
        return new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(), id).build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<NoteResponseDto> getStudentDetails(@PathVariable(value = "id") Long id) {
        NoteDto noteDto = basicNoteService.getNoteDetails(id);
        if (NoteType.IMAGE_NOTE.name().equals(noteDto.getNoteType())) {
            noteDto = imageNoteService.getNoteDetails(id);
        }
        if (NoteType.CHECKBOX_NOTE.name().equals(noteDto.getNoteType())) {
            noteDto = checkBoxNoteService.getNoteDetails(id);
        }
        NoteResponseDto noteResponseDto = new NoteResponseDto(noteDto);
        return new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(),
                noteResponseDto)
                .build();
    }

    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<FilterResponseDto> listAllNote(@RequestParam Integer page,
                                                       @RequestParam Integer pageSize,
                                                       @RequestBody(required = false) NoteFilterRequest filterRequest) {

        Page<BasicNote> basicNotes = basicNoteService.findAll(page, pageSize, filterRequest);

        FilterResponseDto<NoteDto> filterResults = new FilterResponseDto<>();

        filterResults
                .setData(basicNotes
                        .getContent()
                        .stream()
                        .map(basicNote -> BasicNoteMapper.INSTANCE.fromEntity(basicNote))
                        .collect(Collectors.toList()));

        filterResults.setPageSize(basicNotes.getNumberOfElements());
        filterResults.setTotalElements(basicNotes.getTotalElements());
        filterResults.setTotalPages(basicNotes.getTotalPages());

        return new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(), filterResults).build();
    }

}
