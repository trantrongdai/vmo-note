package com.cmc.service.impl;

import com.cmc.constants.MessageCode;
import com.cmc.dto.request.BatchDeleteRequestDto;
import com.cmc.dto.request.NoteRequestDto;
import com.cmc.dto.request.filter.NoteFilterRequest;
import com.cmc.exceptions.AppException;
import com.cmc.exceptions.BadRequestException;
import com.cmc.exceptions.ResourceNotFoundException;
import com.cmc.mapper.BasicNoteMapper;
import com.cmc.model.BasicNote;
import com.cmc.model.User;
import com.cmc.model.dto.NoteDto;
import com.cmc.repository.ImageNoteRepository;
import com.cmc.repository.NoteRepository;
import com.cmc.repository.UserRepository;
import com.cmc.service.NoteConverterStrategy;
import com.cmc.service.BasicNoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class BasicBasicNoteServiceImpl implements BasicNoteService, NoteConverterStrategy<BasicNote> {

    private static final Logger logger = LoggerFactory.getLogger(BasicBasicNoteServiceImpl.class);

    @Autowired
    MessageTranslator messageTranslator;

    @Value("${student.prefix:TT}")
    private String studentPrefix;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private ImageNoteRepository imageNoteRepository;

    @Transactional
    @Override
    public NoteDto createNote(NoteRequestDto noteRequestDto) {
        try {
            BasicNote basicNote = fromRequestDto(noteRequestDto);
            basicNote = noteRepository.save(basicNote);
            return fromEntity(basicNote);
        } catch (Exception e) {
            logger.error("Exception occur when try to create Basic note {} {}", noteRequestDto.getTitle(), e.getMessage());
            throw new AppException("Exception occur when try to create basic note", e.getCause());
        }
    }

    @Transactional
    @Override
    public NoteDto updateNote(Long id, NoteRequestDto noteRequestDto) {
//        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String
//                .format(messageTranslator
//                        .toLocale(MessageCode.STUDENT_ID_NOT_FOUND), id)));
//
//        Clazz clazz = clazzRepository
//                .findById(updateRequestDto.getClazzId())
//                .orElseThrow(() -> new BadRequestException(String
//                        .format(messageTranslator
//                                        .toLocale(MessageCode.CLAZZ_ID_NOT_FOUND)
//                                , updateRequestDto.getClazzId())));
//
//        try {
//            Student entityForUpdate = StudentMapper.INSTANCE.fromRequestDto(updateRequestDto);
//
//            entityForUpdate.setId(id);
//            entityForUpdate.setClazz(clazz);
//            // values of roll number is immutable, so we will keep them
//            entityForUpdate.setRollNumber(student.getRollNumber());
//            Student updatedStudent = studentRepository.save(entityForUpdate);
//            return StudentMapper.INSTANCE.fromEntity(updatedStudent);
//        } catch (Exception e) {
//            logger.error("Exception occur when try to update Student {} {}", id, e.getMessage());
//            throw new AppException("Exception occur when try to update Student", e.getCause());
//        }
        return null;
    }

    @Transactional
    @Override
    public void deleteNote(Long id) {
        BasicNote basicNote = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String
                .format(messageTranslator
                        .toLocale(MessageCode.NOTE_ID_NOT_FOUND), id)));
        try {

            noteRepository.delete(basicNote);
        } catch (Exception e) {
            logger.error("Exception occur when try to delete Note {} {}", id, e.getCause());
            throw new AppException("Exception occur when try to delete Note", e.getCause());
        }
    }

    @Override
    public NoteDto getNoteDetails(Long id) {
//        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String
//                .format(messageTranslator
//                        .toLocale(MessageCode.STUDENT_ID_NOT_FOUND), id)));
//
//        return StudentMapper.INSTANCE.fromEntity(student);
        return null;
    }

    @Override
    public Page<BasicNote> findAll(Integer pageIndex, Integer pageSize, NoteFilterRequest filterRequest) {
//        Integer _pageIndex = PagingConstant.DEFAULT_PAGE_INDEX;
//        Integer _pageSize = PagingConstant.DEFAULT_PAGE_SIZE;
//
//        if (!Objects.isNull(pageIndex)) {
//            _pageIndex = pageIndex;
//        }
//
//        if (!Objects.isNull(pageSize)) {
//            _pageSize = pageSize;
//        }
//        List<Sort.Order> orders = new ArrayList<>();
//
//        if (Objects.nonNull(filterRequest)) {
//            if (MapUtils.isNotEmpty(filterRequest.getSortBy())) {
//                filterRequest.getSortBy().forEach((k, v) -> {
//                    switch (v) {
//                        case "asc":
//                            Sort.Order ascOrder = Sort.Order.asc(k);
//                            orders.add(ascOrder);
//                            break;
//                        case "desc":
//                            Sort.Order descOrder = Sort.Order.desc(k);
//                            orders.add(descOrder);
//                            break;
//                        default:
//                            break;
//                    }
//                });
//            }
//        }
//        Pageable pageable = PageRequest.of(_pageIndex, _pageSize, Sort.by(orders));
//
//        Page<Student> results = Page.empty();
//        try {
//            results = studentRepository.findAll(pageable);
//        } catch (Exception exception) {
//            logger.error("Exception occur when trying to list Student! {}", exception.getMessage());
//            return results;
//        }
//        return results;
        return null;
    }

    @Transactional
    @Override
    public void batchDelete(BatchDeleteRequestDto batchDeleteRequestDto) {
        if (Objects.isNull(batchDeleteRequestDto) || CollectionUtils.isEmpty(batchDeleteRequestDto.getIds())) {
            return;
        }
        batchDeleteRequestDto.getIds()
                .stream()
                .map(id -> noteRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(note -> {
                    try {
                        noteRepository.delete(note);
                    } catch (Exception e) {
                        logger.error("Exception occur when try to delete Note {} {}", note.getId(), e.getCause());
                        throw new AppException("Exception occur when try to delete Note", e.getCause());
                    }
                });
    }

    @Override
    public NoteDto fromEntity(BasicNote basicNote) {
        return BasicNoteMapper.INSTANCE.fromEntity(basicNote);
    }

    @Override
    public BasicNote fromRequestDto(NoteRequestDto requestDto) {
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
}
