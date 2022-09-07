package com.cmc.service.impl;

import com.cmc.constants.MessageCode;
import com.cmc.dto.request.NoteRequestDto;
import com.cmc.enums.NoteType;
import com.cmc.exceptions.AppException;
import com.cmc.exceptions.BadRequestException;
import com.cmc.exceptions.ResourceNotFoundException;
import com.cmc.mapper.BasicNoteMapper;
import com.cmc.model.BasicNote;
import com.cmc.model.CheckBoxNote;
import com.cmc.model.User;
import com.cmc.model.dto.NoteDto;
import com.cmc.repository.BasicNoteRepository;
import com.cmc.repository.CheckBoxNoteRepository;
import com.cmc.repository.UserRepository;
import com.cmc.service.CheckBoxNoteService;
import com.cmc.service.NoteConverterStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class CheckBoxNoteServiceImpl implements CheckBoxNoteService, NoteConverterStrategy<CheckBoxNote> {

    private static final Logger logger = LoggerFactory.getLogger(CheckBoxNoteServiceImpl.class);

    @Autowired
    MessageTranslator messageTranslator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BasicNoteRepository basicNoteRepository;

    @Autowired
    private CheckBoxNoteRepository checkBoxNoteRepository;

    @Transactional
    @Override
    public NoteDto createNote(NoteRequestDto noteRequestDto) {
        try {
            CheckBoxNote checkBoxNote = getCreateEntity(noteRequestDto);
            checkBoxNote = checkBoxNoteRepository.save(checkBoxNote);
            return fromEntity(checkBoxNote);
        } catch (Exception e) {
            logger.error("Exception occur when try to create CheckBox note {} {}", noteRequestDto.getTitle(), e.getMessage());
            throw new AppException("Exception occur when try to create CheckBox note", e.getCause());
        }
    }

    @Transactional
    @Override
    public NoteDto updateNote(Long id, NoteRequestDto noteRequestDto) {
        try {
            CheckBoxNote entityForUpdate = getUpdateEntity(noteRequestDto, id);

            CheckBoxNote updatedNote = checkBoxNoteRepository.save(entityForUpdate);
            return fromEntity(updatedNote);
        } catch (Exception e) {
            logger.error("Exception occur when try to update Check box note {} {}", id, e.getMessage());
            throw new AppException("Exception occur when try to update Check box note", e.getCause());
        }
    }

    @Override
    public NoteDto getNoteDetails(Long id) {
        BasicNote basicNote = basicNoteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String
                .format(messageTranslator
                        .toLocale(MessageCode.BASIC_NOTE_ID_NOT_FOUND), id)));
        CheckBoxNote checkBoxNote = checkBoxNoteRepository.findByBasicNote(basicNote);
        if (Objects.isNull(checkBoxNote)) {
            throw new ResourceNotFoundException(String
                    .format(messageTranslator
                            .toLocale(MessageCode.CHECK_BOX_NOTE_ID_NOT_FOUND), id));
        }
        return fromEntity(checkBoxNote);
    }

    @Override
    public NoteDto fromEntity(CheckBoxNote checkBoxNote) {
        BasicNote basicNote = checkBoxNote.getBasicNote();
        NoteDto noteDto = BasicNoteMapper.INSTANCE.fromEntity(basicNote);
        noteDto.setCheckBoxStatus(checkBoxNote.getCheckBoxStatus());
        return noteDto;
    }

    @Override
    public CheckBoxNote getCreateEntity(NoteRequestDto requestDto) {
        User user = userRepository
                .findById(1l)
                .orElseThrow(() -> new BadRequestException(String
                        .format(messageTranslator
                                        .toLocale(MessageCode.USER_ID_NOT_FOUND)
                                , 1l)));
        BasicNote basicNote = BasicNoteMapper.INSTANCE.fromRequestDto(requestDto);
        basicNote.setUser(user);
        CheckBoxNote checkBoxNote = new CheckBoxNote();
        checkBoxNote.setCheckBoxStatus(requestDto.getCheckBoxStatus());
        checkBoxNote.setBasicNote(basicNote);
        return checkBoxNote;
    }

    @Override
    public CheckBoxNote getUpdateEntity(NoteRequestDto requestDto, Long id) {
        BasicNote basicNote = basicNoteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String
                .format(messageTranslator
                        .toLocale(MessageCode.BASIC_NOTE_ID_NOT_FOUND), id)));
        if (!NoteType.CHECKBOX_NOTE.name().equals(basicNote.getNoteType())) {
            throw new AppException("Note is not check box note!");
        }
        basicNote.setTitle(requestDto.getTitle());
        basicNote.setDescription(requestDto.getDescription());

        CheckBoxNote checkBoxNote = checkBoxNoteRepository.findByBasicNote(basicNote);
        if (Objects.isNull(checkBoxNote)) {
            throw new ResourceNotFoundException(String
                    .format(messageTranslator
                            .toLocale(MessageCode.CHECK_BOX_NOTE_ID_NOT_FOUND), id));
        }

        checkBoxNote.setCheckBoxStatus(requestDto.getCheckBoxStatus());
        checkBoxNote.setBasicNote(basicNote);
        return checkBoxNote;
    }
}
