package com.vmo.note;

import com.vmo.note.model.BasicNote;
import com.vmo.note.model.dto.NoteDto;
import com.vmo.note.repository.BasicNoteRepository;
import com.vmo.note.service.impl.BasicBasicNoteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class BasicBasicNoteServiceImplTests {

    @Mock
    private BasicNoteRepository basicNoteRepository;

    @InjectMocks
    private BasicBasicNoteServiceImpl basicNoteService;

    @Test
    @WithMockUser(username = "linhpv", roles = {"USER"})
    void getNoteDetailsTest() throws Exception {
        Long id = 1l;
        NoteDto noteDto = new NoteDto("title", "description", "BASIC_NOTE");
        BasicNote basicNote = new BasicNote("title", "description", "BASIC_NOTE");
        Optional<BasicNote> basicNoteOptional = Optional.of(basicNote);

        doReturn(basicNoteOptional).when(basicNoteRepository).findById(id);
        NoteDto noteDetails = basicNoteService.getNoteDetails(id);

        assertThat(noteDto.getTitle(), equalTo(noteDetails.getTitle()));
        assertThat(noteDto.getDescription(), equalTo(noteDetails.getDescription()));
        assertThat(noteDto.getNoteType(), equalTo(noteDetails.getNoteType()));
    }

}
