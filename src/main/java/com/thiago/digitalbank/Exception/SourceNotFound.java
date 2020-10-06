package com.thiago.digitalbank.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SourceNotFound extends RuntimeException {

    public SourceNotFound(String message) {
        super(message);


    }

}
