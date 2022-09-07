package com.cmc.service.impl;

import com.cmc.constants.MessageCode;
import com.cmc.constants.PagingConstant;
import com.cmc.dto.request.NoteRequestDto;
import com.cmc.dto.request.filter.NoteFilterRequest;
import com.cmc.enums.NoteType;
import com.cmc.exceptions.AppException;
import com.cmc.exceptions.BadRequestException;
import com.cmc.exceptions.ResourceNotFoundException;
import com.cmc.mapper.BasicNoteMapper;
import com.cmc.model.BasicNote;
import com.cmc.model.User;
import com.cmc.model.dto.NoteDto;
import com.cmc.repository.BasicNoteRepository;
import com.cmc.repository.ImageNoteRepository;
import com.cmc.repository.UserRepository;
import com.cmc.service.BasicNoteService;
import com.cmc.service.CheckBoxNoteService;
import com.cmc.service.ImageNoteService;
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

    @Value("${student.prefix:TT}")
    private String studentPrefix;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BasicNoteRepository basicNoteRepository;

    @Autowired
    private ImageNoteRepository imageNoteRepository;

    @Autowired
    private ImageNoteService imageNoteService;

    @Autowired
    private CheckBoxNoteService checkBoxNoteService;

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
            results = basicNoteRepository.findAll(pageable);
        } catch (Exception exception) {
            logger.error("Exception occur when trying to list Basic note! {}", exception.getMessage());
            return results;
        }
        return results;
    }

    @Override
    public NoteDto fromEntity(BasicNote basicNote) {
        return BasicNoteMapper.INSTANCE.fromEntity(basicNote);
    }

    @Override
    public BasicNote getCreateEntity(NoteRequestDto requestDto) {
        User user = userRepository
                .findById(1l)
                .orElseThrow(() -> new BadRequestException(String
                        .format(messageTranslator
                                        .toLocale(MessageCode.USER_ID_NOT_FOUND)
                                , 1l)));
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
