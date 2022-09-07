package com.cmc.service.impl;

import com.cmc.constants.MessageCode;
import com.cmc.dto.request.NoteRequestDto;
import com.cmc.enums.NoteType;
import com.cmc.exceptions.AppException;
import com.cmc.exceptions.BadRequestException;
import com.cmc.exceptions.ResourceNotFoundException;
import com.cmc.mapper.BasicNoteMapper;
import com.cmc.model.BasicNote;
import com.cmc.model.ImageNote;
import com.cmc.model.User;
import com.cmc.model.dto.NoteDto;
import com.cmc.repository.BasicNoteRepository;
import com.cmc.repository.ImageNoteRepository;
import com.cmc.repository.UserRepository;
import com.cmc.service.ImageNoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class ImageNoteServiceImpl implements ImageNoteService {

    private static final Logger logger = LoggerFactory.getLogger(ImageNoteServiceImpl.class);

    @Autowired
    MessageTranslator messageTranslator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BasicNoteRepository basicNoteRepository;

    @Autowired
    private ImageNoteRepository imageNoteRepository;

    @Transactional
    @Override
    public NoteDto createNote(NoteRequestDto noteRequestDto) {
        try {
            ImageNote imageNote = getCreateEntity(noteRequestDto);
            imageNote = imageNoteRepository.save(imageNote);
            return fromEntity(imageNote);
        } catch (Exception e) {
            logger.error("Exception occur when try to create Image note {} {}", noteRequestDto.getTitle(), e.getMessage());
            throw new AppException("Exception occur when try to create Image note ", e.getCause());
        }
    }

    @Transactional
    @Override
    public NoteDto updateNote(Long id, NoteRequestDto noteRequestDto) {
        try {
            ImageNote entityForUpdate = getUpdateEntity(noteRequestDto, id);

            ImageNote updatedNote = imageNoteRepository.save(entityForUpdate);
            return fromEntity(updatedNote);
        } catch (Exception e) {
            logger.error("Exception occur when try to update Image note {} {}", id, e.getMessage());
            throw new AppException("Exception occur when try to update Image note", e.getCause());
        }
    }

    @Override
    public NoteDto getNoteDetails(Long id) {
        BasicNote basicNote = basicNoteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String
                .format(messageTranslator
                        .toLocale(MessageCode.IMAGE_NOTE_ID_NOT_FOUND), id)));
        ImageNote imageNote = imageNoteRepository.findByBasicNote(basicNote);
        if (Objects.isNull(imageNote)) {
            throw new ResourceNotFoundException(String
                    .format(messageTranslator
                            .toLocale(MessageCode.IMAGE_NOTE_ID_NOT_FOUND), id));
        }
        return fromEntity(imageNote);
    }

    @Override
    public NoteDto fromEntity(ImageNote imageNote) {
        BasicNote basicNote = imageNote.getBasicNote();
        NoteDto noteDto = BasicNoteMapper.INSTANCE.fromEntity(basicNote);
        noteDto.setImageUrl(imageNote.getImageUrl());
        return noteDto;
    }

    @Override
    public ImageNote getCreateEntity(NoteRequestDto requestDto) {
        User user = userRepository
                .findById(1l)
                .orElseThrow(() -> new BadRequestException(String
                        .format(messageTranslator
                                        .toLocale(MessageCode.USER_ID_NOT_FOUND)
                                , 1l)));
        BasicNote basicNote = BasicNoteMapper.INSTANCE.fromRequestDto(requestDto);
        basicNote.setUser(user);
        ImageNote imageNote = new ImageNote();
        imageNote.setImageUrl(requestDto.getImageUrl());
        imageNote.setBasicNote(basicNote);
        return imageNote;
    }

    @Override
    public ImageNote getUpdateEntity(NoteRequestDto requestDto, Long id) {
        BasicNote basicNote = basicNoteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String
                .format(messageTranslator
                        .toLocale(MessageCode.BASIC_NOTE_ID_NOT_FOUND), id)));
        if (!NoteType.IMAGE_NOTE.name().equals(basicNote.getNoteType())) {
            throw new AppException("Note is not image note!");
        }

        basicNote.setTitle(requestDto.getTitle());
        basicNote.setDescription(requestDto.getDescription());

        ImageNote imageNote = imageNoteRepository.findByBasicNote(basicNote);
        if (Objects.isNull(imageNote)) {
            throw new ResourceNotFoundException(String
                    .format(messageTranslator
                            .toLocale(MessageCode.IMAGE_NOTE_ID_NOT_FOUND), id));
        }

        imageNote.setImageUrl(requestDto.getImageUrl());
        imageNote.setBasicNote(basicNote);
        return imageNote;
    }
}
