package com.karan.accountsservice.exception;

import com.karan.accountsservice.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(
            Exception exception,
            WebRequest webRequest
    ) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(
            CustomerAlreadyExistsException ex,
            WebRequest webRequest
    ) {
        ErrorResponseDTO responseDTO = ErrorResponseDTO.builder()
                .apiPath(webRequest.getDescription(false))
                .errorMessage(ex.getMessage())
                .errorCode(HttpStatus.BAD_REQUEST)
                .errorTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest webRequest
    ) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {

        Map<String, String> errors = new HashMap<>();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

        allErrors.forEach((err)->{
            String fieldName = ((FieldError) err).getField();
            String errorMessage = err.getDefaultMessage();
            errors.put(fieldName , errorMessage);
        });

        System.out.println(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
