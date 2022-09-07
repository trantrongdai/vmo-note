package com.vmo.note.service.impl;

import com.vmo.note.constants.MessageCode;
import com.vmo.note.constants.PagingConstant;
import com.vmo.note.dto.request.NoteRequestDto;
import com.vmo.note.dto.request.filter.NoteFilterRequest;
import com.vmo.note.enums.NoteType;
import com.vmo.note.exceptions.AppException;
import com.vmo.note.exceptions.BadRequestException;
import com.vmo.note.exceptions.ResourceNotFoundException;
import com.vmo.note.mapper.BasicNoteMapper;
import com.vmo.note.model.BasicNote;
import com.vmo.note.model.User;
import com.vmo.note.model.dto.NoteDto;
import com.vmo.note.repository.BasicNoteRepository;
import com.vmo.note.service.BasicNoteService;
import com.vmo.note.service.UserService;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BasicBasicNoteServiceImpl implements BasicNoteService {

    private static final Logger logger = LoggerFactory.getLogger(BasicBasicNoteServiceImpl.class);

    @Autowired
    MessageTranslator messageTranslator;

    @Autowired
    private UserService userService;

    @Autowired
    private BasicNoteRepository basicNoteRepository;

    @Transactional
    @Override
    public NoteDto createNote(NoteRequestDto noteRequestDto) {
        try {
            BasicNote basicNote = getCreateEntity(noteRequestDto);
            basicNote = basicNoteRepository.save(basicNote);
            return fromEntity(basicNote);
        } catch (Exception e) {
            logger.error("Exception occur when try to create Basic note {} {}", noteRequestDto.getTitle(), e.getMessage());
            throw new AppException("Exception occur when try to create basic note", e.getCause());
        }
    }

    @Transactional
    @Override
    public NoteDto updateNote(Long id, NoteRequestDto noteRequestDto) {
        BasicNote basicNote = basicNoteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String
                .format(messageTranslator
                        .toLocale(MessageCode.BASIC_NOTE_ID_NOT_FOUND), id)));
        if (!NoteType.BASIC_NOTE.name().equals(basicNote.getNoteType())) {
            throw new AppException("Note is not basic note!");
        }
        try {
            BasicNote entityForUpdate = getUpdateEntity(noteRequestDto, id);

            BasicNote updatedNote = basicNoteRepository.save(entityForUpdate);
            return fromEntity(updatedNote);
        } catch (Exception e) {
            logger.error("Exception occur when try to update Basic note {} {}", id, e.getMessage());
            throw new AppException("Exception occur when try to update Basic note", e.getCause());
        }
    }

    @Transactional
    @Override
    public void deleteNote(Long id) {
        BasicNote basicNote = basicNoteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String
                .format(messageTranslator
                        .toLocale(MessageCode.BASIC_NOTE_ID_NOT_FOUND), id)));
        try {
            basicNoteRepository.delete(basicNote);
        } catch (Exception e) {
            logger.error("Exception occur when try to delete Basic note {} {}", id, e.getCause());
            throw new AppException("Exception occur when try to delete  Basic note", e.getCause());
        }
    }

    @Override
    public NoteDto getNoteDetails(Long id) {
        BasicNote basicNote = basicNoteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String
                .format(messageTranslator
                        .toLocale(MessageCode.BASIC_NOTE_ID_NOT_FOUND), id)));
        return fromEntity(basicNote);
    }

    @Override
    public Page<BasicNote> findAll(Integer pageIndex, Integer pageSize, NoteFilterRequest filterRequest) {
        Integer _pageIndex = PagingConstant.DEFAULT_PAGE_INDEX;
        Integer _pageSize = PagingConstant.DEFAULT_PAGE_SIZE;

        if (!Objects.isNull(pageIndex)) {
            _pageIndex = pageIndex;
        }

        if (!Objects.isNull(pageSize)) {
            _pageSize = pageSize;
        }
        List<Sort.Order> orders = new ArrayList<>();

        if (Objects.nonNull(filterRequest)) {
            if (MapUtils.isNotEmpty(filterRequest.getSortBy())) {
                filterRequest.getSortBy().forEach((k, v) -> {
                    switch (v) {
                        case "asc":
                            Sort.Order ascOrder = Sort.Order.asc(k);
                            orders.add(ascOrder);
                            break;
                        case "desc":
                            Sort.Order descOrder = Sort.Order.desc(k);
                            orders.add(descOrder);
                            break;
                        default:
                            break;
                    }
                });
            }
        }
        Pageable pageable = PageRequest.of(_pageIndex, _pageSize, Sort.by(orders));

        Page<BasicNote> results = Page.empty();
        try {
            results = basicNoteRepository.findAllByUser(userService.getLoggedInUser(), pageable);
        } catch (Exception exception) {
            logger.error("Exception occur when trying to list Basic note! {}", exception.getMessage());
            return results;
        }
        return results;
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
