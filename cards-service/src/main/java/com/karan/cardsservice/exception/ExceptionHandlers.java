package com.karan.cardsservice.exception;

import com.karan.cardsservice.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
public class ExceptionHandlers extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String , String > errors = new HashMap<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

        errorList.forEach((err)->{
            String fName = ((FieldError)err).getField();
            String msg = err.getDefaultMessage();
            errors.put(fName, msg);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(
            Exception ex,
            WebRequest request
    ){
        ErrorResponseDTO responseDTO = new ErrorResponseDTO(
                request.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest request
    ){
        ErrorResponseDTO responseDTO = new ErrorResponseDTO(
                request.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
    }

    @ExceptionHandler(CardAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCardAlreadyExistsException(
            CardAlreadyExistsException ex,
            WebRequest request
    ){
        ErrorResponseDTO responseDTO = new ErrorResponseDTO(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
    }
}
