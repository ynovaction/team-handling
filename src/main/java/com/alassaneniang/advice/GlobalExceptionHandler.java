package com.alassaneniang.advice;

import com.alassaneniang.exception.TeamInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    @ExceptionHandler( TeamInvalidException.class )
    String teamInvalidHandler ( TeamInvalidException exception ) {
        return exception.getMessage();
    }
}
