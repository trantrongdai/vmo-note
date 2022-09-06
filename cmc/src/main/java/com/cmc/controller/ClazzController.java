package com.cmc.controller;

import com.cmc.dto.RestResponse;
import com.cmc.dto.response.ClazzResponseDto;
import com.cmc.enums.StatusCode;
import com.cmc.model.dto.ClazzDto;
import com.cmc.service.ClazzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Clazz controller
 */
@RestController
@RequestMapping("/clazz")
public class ClazzController extends RestBaseController {
    private static final Logger logger = LoggerFactory.getLogger(ClazzController.class);

    @Autowired
    ClazzService clazzService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<ClazzResponseDto> getClazzDetails(@PathVariable(value = "id") Long id) {
        ClazzDto clazzDto = clazzService.findById(id);
        ClazzResponseDto clazzResponseDto = new ClazzResponseDto(clazzDto);
        return new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(),
                clazzResponseDto)
                .build();
    }

    @GetMapping(value = "/list-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<List<ClazzDto>> listAllClazz() {
        List<ClazzDto> clazzDtos = clazzService.listAll();
        return new RestResponse.RestResponseBuilder(StatusCode.SUCCESS.getValue(), clazzDtos).build();
    }

}
