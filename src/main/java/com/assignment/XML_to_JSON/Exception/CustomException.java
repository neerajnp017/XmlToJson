package com.assignment.XML_to_JSON.Exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final String errorCode;
    public CustomException(String message, String erroCode) {
        super(message);
        this.errorCode=erroCode;
    }
}