package com.vmo.note.service.impl;

import com.vmo.note.constants.MessageCode;
import com.vmo.note.dto.request.NoteRequestDto;
import com.vmo.note.enums.NoteType;
import com.vmo.note.exceptions.AppException;
import com.vmo.note.exceptions.BadRequestException;
import com.vmo.note.exceptions.ResourceNotFoundException;
import com.vmo.note.mapper.BasicNoteMapper;
import com.vmo.note.mapper.CheckBoxMapper;
import com.vmo.note.model.BasicNote;
import com.vmo.note.model.CheckBox;
import com.vmo.note.model.CheckBoxNote;
import com.vmo.note.model.User;
import com.vmo.note.model.dto.CheckBoxDto;
import com.vmo.note.model.dto.NoteDto;
import com.vmo.note.repository.BasicNoteRepository;
import com.vmo.note.repository.CheckBoxNoteRepository;
import com.vmo.note.repository.CheckBoxRepository;
import com.vmo.note.repository.UserRepository;
import com.vmo.note.service.CheckBoxNoteService;
import com.vmo.note.service.NoteConverterStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Autowired
    private CheckBoxRepository checkBoxRepository;

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
        List<CheckBox> checkBoxes = checkBoxNote.getCheckBoxes();
        if (Objects.nonNull(checkBoxes)) {
            List<CheckBoxDto> checkBoxDtos = checkBoxes.stream().map(checkBox -> {
                return CheckBoxMapper.INSTANCE.fromEntity(checkBox);
            }).collect(Collectors.toList());
            noteDto.setCheckBoxes(checkBoxDtos);
        }
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

        List<CheckBoxDto> checkBoxDtos = requestDto.getCheckBoxes();
        List<CheckBox> checkBoxes = new ArrayList<>();
        if (Objects.nonNull(checkBoxDtos)) {
            checkBoxDtos.stream().forEach(checkBoxDto -> {
                CheckBox checkBox = CheckBoxMapper.INSTANCE.fromDto(checkBoxDto);
                checkBox.setCheckBoxNote(checkBoxNote);
                checkBoxes.add(checkBox);
            });
        }
        checkBoxNote.setCheckBoxes(checkBoxes);
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

        List<CheckBox> insertOrUpdatingCheckBoxes = new ArrayList<>();
        List<CheckBoxDto> checkBoxDtos = requestDto.getCheckBoxes();
        if (Objects.nonNull(checkBoxDtos)) {
            List<CheckBox> checkBoxes = checkBoxRepository.findByCheckBoxNote(checkBoxNote);
            Map<Long, CheckBox> checkBoxMap = checkBoxes.stream().collect(Collectors.toMap(CheckBox::getId, Function.identity()));

            if (Objects.nonNull(checkBoxes)) {
                checkBoxDtos.stream().forEach(checkBoxDto -> {
                    if (Objects.isNull(checkBoxMap.get(checkBoxDto.getId()))) {
                        CheckBox checkBox = CheckBoxMapper.INSTANCE.fromDto(checkBoxDto);
                        checkBox.setCheckBoxNote(checkBoxNote);
                        insertOrUpdatingCheckBoxes.add(checkBox);
                    } else {
                        CheckBox checkBox = checkBoxMap.get(checkBoxDto.getId());
                        checkBox.setName(checkBoxDto.getName());
                        checkBox.setValue(checkBoxDto.getValue());
                        checkBox.setSelected(checkBoxDto.isSelected());
                        insertOrUpdatingCheckBoxes.add(checkBox);
                    }
                });
            }
        }

        checkBoxNote.setCheckBoxes(insertOrUpdatingCheckBoxes);
        checkBoxNote.setBasicNote(basicNote);
        return checkBoxNote;
    }
}
