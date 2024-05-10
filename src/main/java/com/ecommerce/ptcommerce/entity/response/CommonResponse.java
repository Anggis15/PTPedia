package com.ecommerce.ptcommerce.entity.response;

import lombok.Getter;
import lombok.Setter;

public class CommonResponse {
    @Setter
    @Getter
    private String code;

    @Setter @Getter
    private String status;

    @Setter @Getter
    private String message;

}
