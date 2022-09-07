package com.vmo.note.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmo.note.dto.request.filter.NoteFilterRequest;
import com.vmo.note.exceptions.ResourceNotFoundException;
import com.vmo.note.model.BasicNote;
import com.vmo.note.model.dto.NoteDto;
import com.vmo.note.service.BasicNoteService;
import com.vmo.note.service.CheckBoxNoteService;
import com.vmo.note.service.ImageNoteService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
public class NoteControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BasicNoteService basicNoteService;

    @MockBean
    private ImageNoteService imageNoteService;

    @MockBean
    private CheckBoxNoteService checkBoxNoteService;

    @Test
    void shouldCreateNote() throws Exception {
        NoteDto noteDto = new NoteDto();
        noteDto.setTitle("title");
        noteDto.setDescription("description");
        noteDto.setNoteType("BASIC_NOTE");

        mockMvc.perform(post("/note").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(noteDto)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldReturnNote() throws Exception {
        long id = 1L;
        NoteDto noteDto = new NoteDto();
        noteDto.setTitle("title");
        noteDto.setDescription("description");

        when(basicNoteService.getNoteDetails(id)).thenReturn(noteDto);
        mockMvc.perform(get("/note/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.noteDto.title", Matchers.is("title")))
                .andExpect(jsonPath("$.data.noteDto.description", Matchers.is("description")))
                .andDo(print());
    }

    @Test
    void shouldReturnNotFoundNote() throws Exception {
        long id = 100L;

        when(basicNoteService.getNoteDetails(id)).thenThrow(new ResourceNotFoundException("not founded"));
        mockMvc.perform(get("/note/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void shouldReturnListOfNotes() throws Exception {
        List<BasicNote> notes = new ArrayList<>(
                Arrays.asList(new BasicNote("title1", "description1"),
                        new BasicNote("title2", "description2"),
                        new BasicNote("title3", "description3")));

        PageImpl<BasicNote> page = new PageImpl<>(notes);

        Integer pageIndex = 1;
        Integer pageSize = 10;
        NoteFilterRequest filterRequest = new NoteFilterRequest();
        when(basicNoteService.findAll(pageIndex, pageSize, filterRequest)).thenReturn(page);
        mockMvc.perform(post("/note/list").contentType(MediaType.APPLICATION_JSON)
                .param("page", "1")
                .param("pageSize", "10")
                .content(objectMapper.writeValueAsString(filterRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalElements").value(notes.size()))
                .andDo(print());
    }
//
//  @Test
//  void shouldReturnListOfTutorialsWithFilter() throws Exception {
//    List<Tutorial> tutorials = new ArrayList<>(
//        Arrays.asList(new Tutorial(1, "Spring Boot @WebMvcTest", "Description 1", true),
//            new Tutorial(3, "Spring Boot Web MVC", "Description 3", true)));
//
//    String title = "Boot";
//    MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
//    paramsMap.add("title", title);
//
//    when(tutorialRepository.findByTitleContaining(title)).thenReturn(tutorials);
//    mockMvc.perform(get("/api/tutorials").params(paramsMap))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.size()").value(tutorials.size()))
//        .andDo(print());
//  }
//
//  @Test
//  void shouldReturnNoContentWhenFilter() throws Exception {
//    String title = "BezKoder";
//    MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
//    paramsMap.add("title", title);
//
//    List<Tutorial> tutorials = Collections.emptyList();
//
//    when(tutorialRepository.findByTitleContaining(title)).thenReturn(tutorials);
//    mockMvc.perform(get("/api/tutorials").params(paramsMap))
//        .andExpect(status().isNoContent())
//        .andDo(print());
//  }
//
//  @Test
//  void shouldUpdateTutorial() throws Exception {
//    long id = 1L;
//
//    Tutorial tutorial = new Tutorial(id, "Spring Boot @WebMvcTest", "Description", false);
//    Tutorial updatedtutorial = new Tutorial(id, "Updated", "Updated", true);
//
//    when(tutorialRepository.findById(id)).thenReturn(Optional.of(tutorial));
//    when(tutorialRepository.save(any(Tutorial.class))).thenReturn(updatedtutorial);
//
//    mockMvc.perform(put("/api/tutorials/{id}", id).contentType(MediaType.APPLICATION_JSON)
//        .content(objectMapper.writeValueAsString(updatedtutorial)))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$.title").value(updatedtutorial.getTitle()))
//        .andExpect(jsonPath("$.description").value(updatedtutorial.getDescription()))
//        .andExpect(jsonPath("$.published").value(updatedtutorial.isPublished()))
//        .andDo(print());
//  }
//
//  @Test
//  void shouldReturnNotFoundUpdateTutorial() throws Exception {
//    long id = 1L;
//
//    Tutorial updatedtutorial = new Tutorial(id, "Updated", "Updated", true);
//
//    when(tutorialRepository.findById(id)).thenReturn(Optional.empty());
//    when(tutorialRepository.save(any(Tutorial.class))).thenReturn(updatedtutorial);
//
//    mockMvc.perform(put("/api/tutorials/{id}", id).contentType(MediaType.APPLICATION_JSON)
//        .content(objectMapper.writeValueAsString(updatedtutorial)))
//        .andExpect(status().isNotFound())
//        .andDo(print());
//  }
//
//  @Test
//  void shouldDeleteTutorial() throws Exception {
//    long id = 1L;
//
//    doNothing().when(tutorialRepository).deleteById(id);
//    mockMvc.perform(delete("/api/tutorials/{id}", id))
//         .andExpect(status().isNoContent())
//         .andDo(print());
//  }
//
//  @Test
//  void shouldDeleteAllTutorials() throws Exception {
//    doNothing().when(tutorialRepository).deleteAll();
//    mockMvc.perform(delete("/api/tutorials"))
//         .andExpect(status().isNoContent())
//         .andDo(print());
//  }
}
