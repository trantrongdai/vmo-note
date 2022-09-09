package com.vmo.note.service.impl;

import com.vmo.note.constants.MessageCode;
import com.vmo.note.dto.request.NoteRequestDto;
import com.vmo.note.dto.request.filter.NoteFilterRequest;
import com.vmo.note.dto.response.FilterResponseDto;
import com.vmo.note.enums.NoteType;
import com.vmo.note.exceptions.BadRequestException;
import com.vmo.note.mapper.BasicNoteMapper;
import com.vmo.note.model.BasicNote;
import com.vmo.note.model.User;
import com.vmo.note.model.dto.NoteDto;
import com.vmo.note.repository.BasicNoteRepository;
import com.vmo.note.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * BasicNoteServiceImpl class
 */
@Service
public class NoteServiceImpl implements NoteService {

    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

    @Autowired
    MessageTranslator messageTranslator;

    @Autowired
    private UserService userService;

    @Autowired
    private BasicNoteRepository basicNoteRepository;

    @Autowired
    BasicNoteService basicNoteService;

    @Autowired
    ImageNoteService imageNoteService;

    @Autowired
    CheckBoxNoteService checkBoxNoteService;

    @Transactional
    @Override
    public NoteDto createNote(NoteRequestDto requestDto) {
        NoteDto createdNote = null;
        if (NoteType.BASIC_NOTE.name().equals(requestDto.getNoteType())) {
            createdNote = basicNoteService.createNote(requestDto);
        } else if (NoteType.IMAGE_NOTE.name().equals(requestDto.getNoteType())) {
            createdNote = imageNoteService.createNote(requestDto);
        } else if (NoteType.CHECKBOX_NOTE.name().equals(requestDto.getNoteType())) {
            createdNote = checkBoxNoteService.createNote(requestDto);
        }
        return createdNote;
    }

    @Transactional
    @Override
    public NoteDto updateNote(Long id, NoteRequestDto requestDto) {
        checkingOwner(id);
        NoteDto updatedNote = null;
        if (NoteType.BASIC_NOTE.name().equals(requestDto.getNoteType())) {
            updatedNote = basicNoteService.updateNote(id, requestDto);
        } else if (NoteType.IMAGE_NOTE.name().equals(requestDto.getNoteType())) {
            updatedNote = imageNoteService.updateNote(id, requestDto);
        } else if (NoteType.CHECKBOX_NOTE.name().equals(requestDto.getNoteType())) {
            updatedNote = checkBoxNoteService.updateNote(id, requestDto);
        }
        return updatedNote;
    }

    @Transactional
    @Override
    public void deleteNote(Long id) {
        checkingOwner(id);
        basicNoteService.deleteNote(id);
    }

    @Override
    public NoteDto getNoteDetails(Long id) {
        checkingOwner(id);
        NoteDto noteDto = basicNoteService.getNoteDetails(id);
        if (NoteType.IMAGE_NOTE.name().equals(noteDto.getNoteType())) {
            noteDto = imageNoteService.getNoteDetails(id);
        } else if (NoteType.CHECKBOX_NOTE.name().equals(noteDto.getNoteType())) {
            noteDto = checkBoxNoteService.getNoteDetails(id);
        }
        return noteDto;
    }

    @Override
    public FilterResponseDto<NoteDto> findAll(Integer pageIndex, Integer pageSize, NoteFilterRequest noteFilterRequest) {
        Page<BasicNote> basicNotes = basicNoteService.findAll(pageIndex, pageSize, noteFilterRequest);
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
        return filterResults;
    }

    @Override
    public void checkingOwner(Long noteId) {
        User user = userService.getLoggedInUser();
        if (!basicNoteRepository.existsByUserAndId(user, noteId)) {
            throw new BadRequestException(String
                    .format(messageTranslator
                                    .toLocale(MessageCode.USER_IS_NOT_AN_OWNER_OF_NOTE)
                            , noteId));
        }
    }

    @Override
    public int countUncompletedNote() {
        List<BasicNote> uncompletedNoteOfLoggedInUser = basicNoteRepository
                .findAllByUserAndCompleted(userService.getLoggedInUser(), false);
        return Objects.isNull(uncompletedNoteOfLoggedInUser) ? 0 : uncompletedNoteOfLoggedInUser.size();
    }

    @Override
    public NoteDto fromEntity(BasicNote basicNote) {
        return BasicNoteMapper.INSTANCE.fromEntity(basicNote);
    }

    @Override
    public BasicNote getCreateEntity(NoteRequestDto requestDto) {
        User user = userService.getLoggedInUser();
        BasicNote basicNote = BasicNoteMapper.INSTANCE.fromRequestDto(requestDto);
        basicNote.setUser(user);
        return basicNote;
    }

    @Override
    public BasicNote getUpdateEntity(NoteRequestDto requestDto, Long id) {
        BasicNote basicNote = getCreateEntity(requestDto);
        basicNote.setId(id);
        return basicNote;
    }
}
