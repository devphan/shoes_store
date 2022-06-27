package com.ecommerce.shoes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.shoes.util.ResponseObject;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseObject> handlerNotFoundException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseObject(404, exception.getMessage(), null));
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<ResponseObject> handlerDuplicateRecordException(DuplicateRecordException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseObject(400, exception.getMessage(), null));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseObject> handlerBadRequestException(BadRequestException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseObject(400, exception.getMessage(), null));
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ResponseObject> handlerInternalServerException(InternalServerException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseObject(500, exception.getMessage(), null));
    }

    // handler exception other
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseObject> handlerException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject(500, exception.getMessage(), null));
    }
}
