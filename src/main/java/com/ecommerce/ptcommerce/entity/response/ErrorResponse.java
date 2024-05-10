package com.ecommerce.ptcommerce.entity.response;

public class ErrorResponse extends CommonResponse{
    public ErrorResponse(String code, String message){
        super.setCode("");
        super.setMessage("message");
        super.setStatus("Error");
    }

}
