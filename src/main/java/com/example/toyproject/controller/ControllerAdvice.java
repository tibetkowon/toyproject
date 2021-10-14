package com.example.toyproject.controller;

import com.example.toyproject.common.ResponseCode;
import com.example.toyproject.common.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        String errorKey = e.getBindingResult()
            .getAllErrors()
            .get(0)
            .getDefaultMessage();

        log.error("catch MethodArgumentNotValidException == {}", errorKey);
        ResponseCode responseCode = ResponseCode.valueOf(errorKey);
        return new ResultEntity<>(responseCode);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultEntity handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        log.error("catch handleHttpMessageNotReadableException == {}", e.getMessage());

        return new ResultEntity<>(ResponseCode.REQUEST_NULL);
    }

    @ExceptionHandler(Exception.class)
    public ResultEntity handleException(Exception e){
        log.error("catch handleHttpMessageNotReadableException == {}", e.getMessage());

        return new ResultEntity<>(ResponseCode.ERROR);
    }
}
