package com.sie.demo.handler;


import com.sie.demo.util.ResultJson;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResultJson constraintViolationException(ConstraintViolationException e){
        return new ResultJson(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultJson methodArgumentNotValidException(MethodArgumentNotValidException e){
        return new ResultJson(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResultJson defaultHandler(Exception e){
        System.out.println("error");
        e.printStackTrace();
        return ResultJson.failure();
    }
}
