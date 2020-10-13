package com.thiago.digitalbank.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class EntityRequired extends RuntimeException {
    public EntityRequired(String message) {
        super(message );
    }



}