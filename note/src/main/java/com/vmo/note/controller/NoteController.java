package com.vmo.note.controller;

import com.vmo.note.dto.RestResponse;
import com.vmo.note.dto.request.NoteRequestDto;
import com.vmo.note.dto.request.filter.NoteFilterRequest;
import com.vmo.note.dto.response.FilterResponseDto;
import com.vmo.note.dto.response.NoteResponseDto;
import com.vmo.note.enums.NoteType;
import com.vmo.note.enums.StatusCode;
import com.vmo.note.mapper.BasicNoteMapper;
import com.vmo.note.model.BasicNote;
import com.vmo.note.model.dto.NoteDto;
import com.vmo.note.service.BasicNoteService;
import com.vmo.note.service.CheckBoxNoteService;
import com.vmo.note.service.ImageNoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * Student controller
 */
@RestController
@RequestMapping("/api/v1/note")
public class NoteController extends RestBaseController {
    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    BasicNoteService basicNoteService;

    @Autowired
    ImageNoteService imageNoteService;

    @Autowired
    CheckBoxNoteService checkBoxNoteService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> createNote(@Valid @RequestBody NoteRequestDto requestDto) {
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
        RestResponse response = new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(),
                new NoteResponseDto(createdNote)).build();
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> updateNote(@PathVariable(value = "id") Long id,
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
        RestResponse response = new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(),
                new NoteResponseDto(updatedNote))
                .build();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<Long> deleteNote(@PathVariable(value = "id") Long id) {
        basicNoteService.deleteNote(id);
        return new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(), id).build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> getNoteDetails(@PathVariable(value = "id") Long id) {
        NoteDto noteDto = basicNoteService.getNoteDetails(id);
        if (NoteType.IMAGE_NOTE.name().equals(noteDto.getNoteType())) {
            noteDto = imageNoteService.getNoteDetails(id);
        }
        if (NoteType.CHECKBOX_NOTE.name().equals(noteDto.getNoteType())) {
            noteDto = checkBoxNoteService.getNoteDetails(id);
        }
        NoteResponseDto noteResponseDto = new NoteResponseDto(noteDto);
        RestResponse response = new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(),
                noteResponseDto)
                .build();
        return new ResponseEntity(response, HttpStatus.OK);
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
