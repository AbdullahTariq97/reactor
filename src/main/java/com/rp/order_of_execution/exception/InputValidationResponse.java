package com.rp.order_of_execution.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InputValidationResponse {

    private int errorCode;
    private int input;
    private String message;

}
