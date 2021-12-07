package com.revature.revatrade.shared;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
public class GenericInvalidMessage {
    private Long timestamp = new Date().getTime();
    private int status;
    private String message;
    private String url;

    private Map<String, String> validationErrors;

    public GenericInvalidMessage(int status, String message, String url) {
        this.status = status;
        this.message = message;
        this.url = url;
    }
}
