package com.example.toyproject.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResultEntity<T> {

    private String code = ResponseCode.OK.getCode();
    private String message = ResponseCode.OK.getMessage();

    private T data;

    public ResultEntity(T data){
        this.data = data;
    }

    public ResultEntity(ResponseCode responseCode){
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }
}
