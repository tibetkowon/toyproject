package com.example.toyproject.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    OK("0000","OK")

    ,RESTAURANT_PRESENT_NAME("1001","이미 있는 식당명")
    ,RESTAURANT_NO_NAME("1002", "이름 미입력")
    ,RESTAURANT_NO_ADDRESS("1003", "주소 미입력")
    ,RESTAURANT_NO_PHONE_NUM("1004", "전화번호 미입력")
    ,RESTAURANT_NO_RESTAURANT("1005","존재 하지 않는 식당")
    ,RESTAURANT_DEL_RESTAURANT("1006", "삭제된 식당")

    ,ORDER_NO_CUSTOMER_NAME("2001","고객명 미입력")
    ,ORDER_NO_RESTAURANT("2002","식당 ID 미입력")
    ,ORDER_ABSENT_RESTAURANT("2003", "존재하지 않는 식당 ID")
    ,ORDER_NO_ORDER("2004","존재하지 않는 주문")
    ,ORDER_NO_MENU("2005","메뉴 미입력")
    ,ORDER_DEL_ORDER("2006", "삭제된 주문")

    ,REQUEST_NULL("9001","요청값 없음")
    ,ERROR("9999","error")
    ;

    String code;
    String message;

    public String getKey(){
        return this.toString();
    }
}
