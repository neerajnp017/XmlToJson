package com.assignment.XML_to_JSON.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class XmlExxeptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleXMLToJSONException(CustomException exception) {
        ErrorResponse response = ErrorResponse.builder().errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode()).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
