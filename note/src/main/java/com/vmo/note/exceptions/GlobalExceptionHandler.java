package com.vmo.note.exceptions;

import com.vmo.note.dto.RestResponse;
import com.vmo.note.enums.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Class to handle all exception before return to user site
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ResourceNotFoundException handler
     * @param exception
     * @return
     */
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<RestResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {

        RestResponse response = new RestResponse
                .RestResponseBuilder(StatusCode.FAILED.getValue(), null)
                .devMessage(exception.getMessage())
                .userMessage(exception.getMessage())
                .build();

        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    /**
     * BadRequestException handler
     * @param exception
     * @return
     */
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<RestResponse> handleBadRequestException(BadRequestException exception) {

        RestResponse response = new RestResponse
                .RestResponseBuilder(StatusCode.FAILED.getValue(), exception.getData())
                .devMessage(exception.getMessage())
                .userMessage(exception.getMessage())
                .build();

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * AppException handler
     * @param exception
     * @return
     */
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<RestResponse> handleAppException(AppException exception) {

        RestResponse response = new RestResponse
                .RestResponseBuilder(StatusCode.FAILED.getValue(), null)
                .devMessage(exception.getMessage())
                .userMessage(exception.getMessage())
                .build();

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}
