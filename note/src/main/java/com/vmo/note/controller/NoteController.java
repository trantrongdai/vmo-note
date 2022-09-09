package com.vmo.note.controller;

import com.vmo.note.dto.RestResponse;
import com.vmo.note.dto.request.NoteRequestDto;
import com.vmo.note.dto.request.filter.NoteFilterRequest;
import com.vmo.note.dto.response.FilterResponseDto;
import com.vmo.note.dto.response.NoteResponseDto;
import com.vmo.note.enums.StatusCode;
import com.vmo.note.model.dto.NoteDto;
import com.vmo.note.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Note controller
 */
@RestController
@RequestMapping("/api/v1/notes")
public class NoteController extends RestBaseController {
    private static final Logger logger = LoggerFactory.getLogger(NoteController.class);
    @Autowired
    NoteService noteService;

    /**
     * Create note
     *
     * @param requestDto
     * @return ResponseEntity
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> createNote(@Valid @RequestBody NoteRequestDto requestDto) {
        NoteDto createdNote = noteService.createNote(requestDto);
        RestResponse response = new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(),
                new NoteResponseDto(createdNote)).build();
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    /**
     * update Note
     *
     * @param id
     * @param requestDto
     * @return ResponseEntity
     */
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> updateNote(@PathVariable(value = "id") Long id,
                                                   @Valid @RequestBody NoteRequestDto requestDto) {
        NoteDto updatedNote = noteService.updateNote(id, requestDto);
        RestResponse response = new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(),
                new NoteResponseDto(updatedNote))
                .build();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * deleteNote
     *
     * @param id
     * @return RestResponse with deletedNote id
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<Long> deleteNote(@PathVariable(value = "id") Long id) {
        noteService.deleteNote(id);
        return new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(), id).build();
    }

    /**
     * Get note detail by id
     *
     * @param id
     * @return ResponseEntity
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> getNoteDetails(@PathVariable(value = "id") Long id) {
        NoteDto noteDto = noteService.getNoteDetails(id);
        NoteResponseDto noteResponseDto = new NoteResponseDto(noteDto);
        RestResponse response = new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(),
                noteResponseDto)
                .build();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * list all note
     *
     * @param page
     * @param pageSize
     * @return RestResponse
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<FilterResponseDto> listAllNote(@RequestParam Integer page,
                                                       @RequestParam Integer pageSize,
                                                       @RequestBody @Valid NoteFilterRequest noteFilterRequest) {
        FilterResponseDto<NoteDto> filterResults = noteService.findAll(page, pageSize, noteFilterRequest);
        return new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(), filterResults).build();
    }

    /**
     * Count all un completed note
     *
     * @return number of un completed note
     */
    @GetMapping(value = "/count-uncompleted", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> countAllUnCompletedNote() {
        int numberOfUncompletedNote = noteService.countUncompletedNote();
        RestResponse response = new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(),
                numberOfUncompletedNote)
                .build();
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
