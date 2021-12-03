package com.revature.revatrade.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResponse {
    Boolean success;
    String message;
    Object data;
}
