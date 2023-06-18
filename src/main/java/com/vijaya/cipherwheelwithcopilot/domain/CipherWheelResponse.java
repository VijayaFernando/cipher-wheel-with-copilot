package com.vijaya.cipherwheelwithcopilot.domain;

// cipher wheel response
// contain encoded message and status
// use lombok

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CipherWheelResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String encodedMessage;
    private String status;

    public CipherWheelResponse(String status) {
        this.status = status;
    }
}
