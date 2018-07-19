package com.devopstools.portal.exception;

import com.devopstools.portal.model.dto.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pubudu on 8/14/17.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDto> handleSmartBankException(CustomException e, HttpServletRequest request) {

        // Handle the exception here. The status code is usually 400
        HttpStatus status = HttpStatus.valueOf(e.getStatusCode());

        ErrorDto error = new ErrorDto(e.getStatusCode(), status.name(), e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<ErrorDto>(error, headers, status);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handleRuntimeException(RuntimeException e, HttpServletRequest request) {

        // Handle the exception here. The status code is always 500
        int errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

        HttpStatus status = HttpStatus.valueOf(errorCode);
        ErrorDto error = new ErrorDto(errorCode, status.name(), e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<ErrorDto>(error, headers, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e, HttpServletRequest request) {

        // Handle the exception here. The status code is always 500
        int errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

        HttpStatus status = HttpStatus.valueOf(errorCode);
        ErrorDto error = new ErrorDto(errorCode, status.name(), e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<ErrorDto>(error, headers, status);
    }
}
