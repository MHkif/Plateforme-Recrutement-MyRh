package mhkif.yc.myrh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(NotFoundException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setPath(e.getStackTrace()[0].getClassName());
        exceptionResponse.setRequestMethod(e.getStackTrace()[0].getMethodName());
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND);
        exceptionResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setTimeStamp(LocalDateTime.now().toString());
        exceptionResponse.setMessage(e.getMessage());

       return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setPath(e.getStackTrace()[0].getClassName());
        exceptionResponse.setRequestMethod(e.getStackTrace()[0].getMethodName());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST);
        exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setTimeStamp(LocalDateTime.now().toString());
        exceptionResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = InternalServerException.class)
    public ResponseEntity<Object> handleInternalServer(InternalServerException e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setPath(e.getStackTrace()[0].getClassName());
        exceptionResponse.setRequestMethod(e.getStackTrace()[0].getMethodName());
        exceptionResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        exceptionResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exceptionResponse.setTimeStamp(LocalDateTime.now().toString());
        exceptionResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleNoHandlerException(Exception e){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setPath(e.getStackTrace()[0].getClassName());
        exceptionResponse.setRequestMethod(e.getStackTrace()[0].getMethodName());
        exceptionResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        exceptionResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exceptionResponse.setTimeStamp(LocalDateTime.now().toString());
        exceptionResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
