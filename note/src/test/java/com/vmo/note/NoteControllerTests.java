package com.vmo.note;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmo.note.dto.request.filter.NoteFilterRequest;
import com.vmo.note.exceptions.ResourceNotFoundException;
import com.vmo.note.model.BasicNote;
import com.vmo.note.model.dto.CheckBoxDto;
import com.vmo.note.model.dto.NoteDto;
import com.vmo.note.service.BasicNoteService;
import com.vmo.note.service.CheckBoxNoteService;
import com.vmo.note.service.ImageNoteService;
import com.vmo.note.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
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

    @MockBean
    UserService userService;

    @Test
    @WithMockUser(username = "linhpv", roles = {"USER"})
    void shouldCreateNote() throws Exception {
        NoteDto noteDto = new NoteDto("title", "description", "BASIC_NOTE");

        mockMvc.perform(post("/api/v1/note").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(noteDto)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "linhpv", roles = {"USER"})
    void shouldReturnNote() throws Exception {
        long id = 1L;
        NoteDto noteDto = new NoteDto("title", "description", "BASIC_NOTE");

        when(basicNoteService.getNoteDetails(id)).thenReturn(noteDto);
        mockMvc.perform(get("/api/v1/note/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.noteDto.title", Matchers.is("title")))
                .andExpect(jsonPath("$.data.noteDto.description", Matchers.is("description")))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "linhpv", roles = {"USER"})
    void shouldReturnNotFoundNote() throws Exception {
        long id = 100L;

        when(basicNoteService.getNoteDetails(id)).thenThrow(new ResourceNotFoundException("not founded"));
        mockMvc.perform(get("/api/v1/note/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "linhpv", roles = {"USER"})
    void shouldUpdateBasicNote() throws Exception {
        long id = 1L;

        NoteDto noteDto = new NoteDto("title", "description", "BASIC_NOTE");
        NoteDto updatedNote = new NoteDto("title update", "description update", "BASIC_NOTE");

        when(basicNoteService.getNoteDetails(id)).thenReturn(noteDto);
        when(basicNoteService.updateNote(Mockito.anyLong(), Mockito.any())).thenReturn(updatedNote);

        mockMvc.perform(put("/api/v1/note/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedNote)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.noteDto..title").value(updatedNote.getTitle()))
                .andExpect(jsonPath("$.data.noteDto.description").value(updatedNote.getDescription()))
                .andExpect(jsonPath("$.data.noteDto.noteType").value("BASIC_NOTE"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "linhpv", roles = {"USER"})
    void shouldUpdateImageNote() throws Exception {
        long id = 1L;

        NoteDto noteDto = new NoteDto.NoteDtoBuilder("title", "description", "IMAGE_NOTE")
                .imageUrl("abc.jpg")
                .build();
        NoteDto updatedNote = new NoteDto.NoteDtoBuilder("title update", "description-update", "IMAGE_NOTE")
                .imageUrl("abc-update.jpg")
                .build();
        noteDto.setImageUrl("abc-update.jpg");

        when(imageNoteService.getNoteDetails(id)).thenReturn(noteDto);
        when(imageNoteService.updateNote(Mockito.anyLong(), Mockito.any())).thenReturn(updatedNote);

        mockMvc.perform(put("/api/v1/note/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedNote)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.noteDto.title").value(updatedNote.getTitle()))
                .andExpect(jsonPath("$.data.noteDto.description").value(updatedNote.getDescription()))
                .andExpect(jsonPath("$.data.noteDto.noteType").value("IMAGE_NOTE"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "linhpv", roles = {"USER"})
    void shouldUpdateCheckBoxNote() throws Exception {
        long id = 1L;

        CheckBoxDto checkBox1 = new CheckBoxDto(1l, "country", "vnam");
        CheckBoxDto checkBox2 = new CheckBoxDto(2l, "country", "Sing");
        List<CheckBoxDto> checkBoxDtos = Arrays.asList(checkBox1, checkBox2);

        CheckBoxDto checkBox3 = new CheckBoxDto(1l, "country", "Thai");
        CheckBoxDto checkBox4 = new CheckBoxDto(2l, "country", "Korea");
        List<CheckBoxDto> updatedcheckBoxDtos = Arrays.asList(checkBox1, checkBox2);

        NoteDto noteDto = new NoteDto.NoteDtoBuilder("title", "description", "CHECKBOX_NOTE")
                .checkBoxes(checkBoxDtos)
                .build();
        NoteDto updatedNote = new NoteDto.NoteDtoBuilder("title update", "description-update", "CHECKBOX_NOTE")
                .checkBoxes(updatedcheckBoxDtos)
                .build();
        noteDto.setImageUrl("abc-update.jpg");

        when(checkBoxNoteService.getNoteDetails(id)).thenReturn(noteDto);
        when(checkBoxNoteService.updateNote(Mockito.anyLong(), Mockito.any())).thenReturn(updatedNote);

        mockMvc.perform(put("/api/v1/note/{id}", id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedNote)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.noteDto.title").value(updatedNote.getTitle()))
                .andExpect(jsonPath("$.data.noteDto.description").value(updatedNote.getDescription()))
                .andExpect(jsonPath("$.data.noteDto.noteType").value("CHECKBOX_NOTE"))
                .andDo(print());
    }


    @Test
    @WithMockUser(username = "linhpv", roles = {"USER"})
    void shouldReturnListOfNotes() throws Exception {
        List<BasicNote> notes = new ArrayList<>(
                Arrays.asList(new BasicNote("title1", "description1"),
                        new BasicNote("title2", "description2"),
                        new BasicNote("title3", "description3")));

        Page<BasicNote> page = new PageImpl<>(notes);

        Integer pageIndex = 1;
        Integer pageSize = 10;
        NoteFilterRequest filterRequest = new NoteFilterRequest();
        when(basicNoteService.findAll(Mockito.anyInt(), Mockito.anyInt(), Mockito.any())).thenReturn(page);
        mockMvc.perform(post("/api/v1/note/list").contentType(MediaType.APPLICATION_JSON)
                .param("page", "1")
                .param("pageSize", "10")
                .content(objectMapper.writeValueAsString(filterRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.totalElements").value(notes.size()))
                .andDo(print());
    }

}
