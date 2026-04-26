package com.bank.cardservice.exception.handler;

import com.bank.cardservice.dto.ApiErrorResponse;
import com.bank.cardservice.dto.ValidationErrorResponse;
import com.bank.cardservice.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.collections4.map.AbstractOrderedMapDecorator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            CardAlreadyActiveException.class,
            CardAlreadyBlockedException.class,
            CardAlreadyClosedException.class,
            CustomerCardLimitExceededException.class
    })
    public ResponseEntity<ApiErrorResponse> handleConflict(RuntimeException ex, HttpServletRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;

        return ResponseEntity.status(status)
                .body(buildErrorResponse(ex, request, status));
    }
    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<ApiErrorResponse>handleCardNotFoundException(CardNotFoundException ex, HttpServletRequest request){


        HttpStatus status = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(status).body(buildErrorResponse(ex,request,status));
    }


    @ExceptionHandler({
            InvalidCardOperationException.class,
            NoFieldsProvidedForUpdateException.class
    })
    public ResponseEntity<ApiErrorResponse> handleBadRequest(RuntimeException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(buildErrorResponse(ex, request, status));
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationErrorResponse>handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,HttpServletRequest request){

      Map<String,String> errors=new HashMap<>();

      ex.getBindingResult().getFieldErrors().forEach(err ->errors.put(err.getField(),err.getDefaultMessage()));

      HttpStatus status= HttpStatus.BAD_REQUEST;

      ValidationErrorResponse response=ValidationErrorResponse.builder()
              .error(status.getReasonPhrase())
              .timestamp(LocalDateTime.now())
              .status(status.value())
              .message("Validation error")
              .errors(errors)
              .path(request.getRequestURI())
              .build();

      return ResponseEntity.status(status).body(response);


  }
  public ApiErrorResponse buildErrorResponse(Exception ex,HttpServletRequest request,HttpStatus status){

      return ApiErrorResponse.builder()
              .error(status.getReasonPhrase())
              .path(request.getRequestURI())
              .message(ex.getMessage())
              .timestamp(LocalDateTime.now())
              .status(status.value())
              .build();
  }






}
