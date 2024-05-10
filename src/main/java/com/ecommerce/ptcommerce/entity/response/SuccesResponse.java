package com.ecommerce.ptcommerce.entity.response;

import org.springframework.http.HttpStatus;

import com.ecommerce.ptcommerce.constant.SuccesCode;

import lombok.Getter;
import lombok.Setter;

public class SuccesResponse<T> extends CommonResponse {
    @Getter
    @Setter
    T data;

    public SuccesResponse(String message, T data){
        super.setCode(SuccesCode.SS.toString());
        super.setMessage(message);
        setStatus(HttpStatus.OK.name());
        this.data = data;
    }
}
