package com.alten.customer.exception.handler;

import com.alten.customer.dto.ExceptionDto;
import com.alten.customer.exception.NoCustomerFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionHandler {


    @ExceptionHandler(NoCustomerFoundException.class)
    public ResponseEntity<ExceptionDto> handle(NoCustomerFoundException e) {
        HttpStatus responseCode = HttpStatus.NOT_FOUND;
        return createResponseEntity(e, responseCode);
    }

    private ResponseEntity createResponseEntity(Exception e,
                                                HttpStatus responseCode) {
        ExceptionDto error = new ExceptionDto(e.getMessage());
        return new ResponseEntity(error, responseCode);
    }

}
