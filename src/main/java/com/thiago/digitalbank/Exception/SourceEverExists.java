package com.thiago.digitalbank.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SourceEverExists extends RuntimeException {

    public SourceEverExists(String message) {
        super(message );
    }



}