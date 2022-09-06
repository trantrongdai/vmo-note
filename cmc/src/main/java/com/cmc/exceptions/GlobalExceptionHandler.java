package com.cmc.exceptions;

import com.cmc.dto.RestResponse;
import com.cmc.enums.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

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

        return new ResponseEntity(response, HttpStatus.OK);
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

        return new ResponseEntity(response, HttpStatus.OK);
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

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
