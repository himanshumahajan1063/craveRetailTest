package com.example.craveRetail.errorHandling;

import com.example.craveRetail.errorHandling.exception.CraveRetailServiceException;
import com.example.craveRetail.errorHandling.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorDetails.builder()
                                .errorCode("Err-01")
                                .title("User not found.")
                                .errorDetails("User requested could not be found.")
                                .build());
    }

    @ExceptionHandler({CraveRetailServiceException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDetails> handleServiceException(CraveRetailServiceException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ErrorDetails.builder()
                                .errorCode("Err-02")
                                .title("Internal Server Error.")
                                .errorDetails("Error occurred in service while performing a task.")
                                .build());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDetails> handleDefaultException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ErrorDetails.builder()
                                .errorCode("Err-Default")
                                .title("Internal Server Error.")
                                .errorDetails("Exception occurred.")
                                .build());
    }
}
