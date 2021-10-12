package com.example.toyproject.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    OK("0000","OK")

    ,RESTAURANT_PRESENT_NAME("1001","이미 존재하는 식당명 입니다.")
    ,RESTAURANT_NO_NAME("1002", "식당 이름이 비어 있습니다.")
    ,RESTAURANT_NO_ADDRESS("1003", "식당 주소가 비어 있습니다.")
    ,RESTAURANT_NO_PHONENUM("1004", "식당 번호가 비어 있습니다.")


    ,ERROR("9999","error")
    ,;

    String code;
    String message;
}
