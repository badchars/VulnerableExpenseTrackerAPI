package com.oy.expensetrackerapi.exception;

import com.oy.expensetrackerapi.entity.ErrorObject;
import com.oy.expensetrackerapi.entity.Expense;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


// TODO: Vulnerability-2 Improper Error Handling
// Secure Code - Need to use ControllerAdvice annotation
@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleExpenseNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NO_CONTENT.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<Object> handleItemExistsException(ItemAlreadyExistsException ex, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.CONTINUE.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<Object>(errorObject, HttpStatus.CONFLICT);
    }

    // TODO: Farkli bir endpoint olustur ve Custom Exception kullanma. SQL Hatasindan MySQL oldugu anlasilsin. Orn. GET metodu ile calisan bir endpointe POST ile de istek gonderildiginde customexception tanimli olmasin ve sql hatasi gorunsun.

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObject> handleException(MethodArgumentTypeMismatchException ex, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)   
    public ResponseEntity<ErrorObject> handleGeneralException(Exception ex,WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("timestamp", new Date());
        body.put("statusCode", HttpStatus.BAD_REQUEST.value());

        List<String> errors = ex.getBindingResult()
                        .getFieldErrors()
                                .stream()
                                        .map(x->x.getDefaultMessage())
                                                .collect(Collectors.toList());

        body.put("messages", errors);
        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
    }
}
