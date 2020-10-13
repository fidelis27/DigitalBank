package com.thiago.digitalbank.advice;

import com.thiago.digitalbank.Exception.EntityRequired;
import com.thiago.digitalbank.Exception.SourceEverExists;
import com.thiago.digitalbank.Exception.SourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(SourceNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrosApi sourceNotFound(SourceNotFound e) {
        return new ErrosApi(e.getMessage());
    }

    @ExceptionHandler(SourceEverExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrosApi sourceEverExists(SourceEverExists e) {
        return new ErrosApi(e.getMessage());
    }

    @ExceptionHandler(EntityRequired.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrosApi entityRequired(EntityRequired e) {
        return new ErrosApi(e.getMessage());
    }
}
