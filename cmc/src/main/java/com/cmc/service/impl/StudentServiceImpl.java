package com.cmc.service.impl;

import com.cmc.constants.MessageCode;
import com.cmc.constants.PagingConstant;
import com.cmc.dto.request.BatchDeleteRequestDto;
import com.cmc.dto.request.StudentRequestDto;
import com.cmc.dto.request.filter.StudentFilterRequest;
import com.cmc.exceptions.AppException;
import com.cmc.exceptions.BadRequestException;
import com.cmc.exceptions.ResourceNotFoundException;
import com.cmc.mapper.StudentMapper;
import com.cmc.model.Clazz;
import com.cmc.model.Student;
import com.cmc.model.dto.StudentDto;
import com.cmc.repository.ClazzRepository;
import com.cmc.repository.StudentRepository;
import com.cmc.service.StudentService;
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
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    MessageTranslator messageTranslator;

    @Value("${student.prefix:TT}")
    private String studentPrefix;

    @Autowired
    private ClazzRepository clazzRepository;

    @Autowired
    private StudentRepository studentRepository;


    @Transactional
    @Override
    public StudentDto createStudent(StudentRequestDto createRequestDto) {
        Clazz clazz = clazzRepository
                .findById(createRequestDto.getClazzId())
                .orElseThrow(() -> new BadRequestException(String
                        .format(messageTranslator
                                        .toLocale(MessageCode.CLAZZ_ID_NOT_FOUND)
                                , createRequestDto.getClazzId())));

        try {
            Student student = StudentMapper.INSTANCE.fromRequestDto(createRequestDto);

            student.setRollNumber(UUID.randomUUID().toString());
            student.setClazz(clazz);

            student = studentRepository.save(student);
            student.setRollNumber(studentPrefix.concat(String.valueOf(student.getId())));

            student = studentRepository.save(student);
            return StudentMapper.INSTANCE.fromEntity(student);
        } catch (Exception e) {
            logger.error("Exception occur when try to create student {} {}", createRequestDto.getName(), e.getMessage());
            throw new AppException("Exception occur when try to create student", e.getCause());
        }
    }

    @Transactional
    @Override
    public StudentDto updateStudent(Long id, StudentRequestDto updateRequestDto) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String
                .format(messageTranslator
                        .toLocale(MessageCode.STUDENT_ID_NOT_FOUND), id)));

        Clazz clazz = clazzRepository
                .findById(updateRequestDto.getClazzId())
                .orElseThrow(() -> new BadRequestException(String
                        .format(messageTranslator
                                        .toLocale(MessageCode.CLAZZ_ID_NOT_FOUND)
                                , updateRequestDto.getClazzId())));

        try {
            Student entityForUpdate = StudentMapper.INSTANCE.fromRequestDto(updateRequestDto);

            entityForUpdate.setId(id);
            entityForUpdate.setClazz(clazz);
            // values of roll number is immutable, so we will keep them
            entityForUpdate.setRollNumber(student.getRollNumber());
            Student updatedStudent = studentRepository.save(entityForUpdate);
            return StudentMapper.INSTANCE.fromEntity(updatedStudent);
        } catch (Exception e) {
            logger.error("Exception occur when try to update Student {} {}", id, e.getMessage());
            throw new AppException("Exception occur when try to update Student", e.getCause());
        }
    }

    @Transactional
    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String
                .format(messageTranslator
                        .toLocale(MessageCode.STUDENT_ID_NOT_FOUND), id)));
        try {

            studentRepository.delete(student);
        } catch (Exception e) {
            logger.error("Exception occur when try to delete Student {} {}", id, e.getCause());
            throw new AppException("Exception occur when try to delete Student", e.getCause());
        }
    }

    @Override
    public StudentDto getStudentDetails(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String
                .format(messageTranslator
                        .toLocale(MessageCode.STUDENT_ID_NOT_FOUND), id)));

        return StudentMapper.INSTANCE.fromEntity(student);
    }

    @Override
    public Page<Student> findAll(Integer pageIndex, Integer pageSize, StudentFilterRequest filterRequest) {
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

        Page<Student> results = Page.empty();
        try {
            results = studentRepository.findAll(pageable);
        } catch (Exception exception) {
            logger.error("Exception occur when trying to list Student! {}", exception.getMessage());
            return results;
        }
        return results;
    }

    @Transactional
    @Override
    public void batchDelete(BatchDeleteRequestDto batchDeleteRequestDto) {
        if (Objects.isNull(batchDeleteRequestDto) || CollectionUtils.isEmpty(batchDeleteRequestDto.getIds())) {
            return;
        }
        batchDeleteRequestDto.getIds()
                .stream()
                .map(id -> studentRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(center -> {
                    try {
                        studentRepository.delete(center);
                    } catch (Exception e) {
                        logger.error("Exception occur when try to delete Student {} {}", center.getId(), e.getCause());
                        throw new AppException("Exception occur when try to delete Student", e.getCause());
                    }
                });
    }

}
