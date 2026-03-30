package com.bank.customerservice.exceptionhandler;


import com.bank.customerservice.exception.ApiErrorResponse;
import com.bank.customerservice.exception.CustomerNotFoundException;
import com.bank.customerservice.exception.EmailAlreadyExistsException;
import com.bank.customerservice.exception.ValidationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex,HttpServletRequest request){
     HttpStatus status =HttpStatus.NOT_FOUND;
    return ResponseEntity.status(status).body(buildErrorResponse(status ,ex,request));

}
   @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex,HttpServletRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
         return ResponseEntity.status(status).body(buildErrorResponse(status,ex,request));
}


@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ValidationErrorResponse> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request){
    Map<String,String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(err ->errors.put(err.getField(),err.getDefaultMessage()));

  HttpStatus status =HttpStatus.BAD_REQUEST;

   ValidationErrorResponse response =  ValidationErrorResponse.builder()
        .timestamp(LocalDateTime.now())
        .status(status.value())
        .error(status.getReasonPhrase())
        .message("Validation failed")
        .path(request.getRequestURI())
        .errors(errors)
        .build();

   return ResponseEntity.status(status).body(response);
}



private ApiErrorResponse buildErrorResponse(HttpStatus status,Exception ex,HttpServletRequest request){
     return ApiErrorResponse.builder()
             .error(status.getReasonPhrase())
             .timestamp(LocalDateTime.now())
             .message(ex.getMessage())
             .path(request.getRequestURI())
             .status(status.value())
             .build();
}


}
