package com.example.demo.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {

    protected String code;
    protected String message;

    public BaseResponse(String message, String code) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse() {
    }

    public BaseResponse(BaseResponse baseResponse) {
        this.code = baseResponse.code;
        this.message = baseResponse.message;
    }

}
