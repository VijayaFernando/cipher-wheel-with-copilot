package com.vijaya.cipherwheelwithcopilot.domain;

// cipher wheel request
// contain key, plain message, and cipher wheel
// use lombok

import lombok.Data;

@Data
public class CipherWheelRequest {
    private int key;
    private String plainMessage;
    private String cipherWheel;
}
