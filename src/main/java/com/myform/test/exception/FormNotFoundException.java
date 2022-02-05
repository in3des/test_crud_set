package com.myform.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a city was not found.
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FormNotFoundException extends Exception {

    private static final long serialVersionUID = 1105045175631879877L;

    public FormNotFoundException(String message) {
        super(message);
    }

}
