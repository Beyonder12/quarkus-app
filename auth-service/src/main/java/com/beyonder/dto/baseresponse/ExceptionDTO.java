package com.beyonder.dto.baseresponse;

public class ExceptionDTO {
    Integer code;
    String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExceptionDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
