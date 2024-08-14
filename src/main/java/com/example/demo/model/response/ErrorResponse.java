package com.example.demo.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse<T> extends BaseResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T details;

    public ErrorResponse(String code, String message) {
        super(code, message);
    }

    public ErrorResponse(BaseResponse baseResponse) {
        super(baseResponse);
    }


    public ErrorResponse<T> fail(T details) {
        this.details = details;
        return this;
    }
}
