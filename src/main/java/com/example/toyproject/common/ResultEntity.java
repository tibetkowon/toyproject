package com.example.toyproject.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class ResultEntity<T> {

    @NonNull
    private String code = ResponseCode.OK.getCode();

    @NonNull
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
