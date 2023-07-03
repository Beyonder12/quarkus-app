package com.beyonder.dto.base;

public class SuccessResponseDTO {
    private String code;
    private String message;

    public SuccessResponseDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
