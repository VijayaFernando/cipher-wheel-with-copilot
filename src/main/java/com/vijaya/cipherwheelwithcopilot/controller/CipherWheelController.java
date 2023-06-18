package com.vijaya.cipherwheelwithcopilot.controller;

import com.vijaya.cipherwheelwithcopilot.domain.CipherWheelRequest;
import com.vijaya.cipherwheelwithcopilot.domain.CipherWheelResponse;
import com.vijaya.cipherwheelwithcopilot.service.CipherWheelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CipherWheelController {

    private final CipherWheelService cipherWheelService;

    //Get Endpoint to encode the message
    // Request Body : CipherWheelRequest
    // Response Body : CipherWheelResponse
    @GetMapping("/encode")
    public ResponseEntity<CipherWheelResponse> encodePlainText(@RequestBody CipherWheelRequest cipherWheelRequest) {


        // validate the request body
        if (cipherWheelRequest.getKey() == 0) {
            // Should return appropriate response code
            return new ResponseEntity<>(new CipherWheelResponse("Key is zero"), HttpStatus.BAD_REQUEST);
        }

        if (cipherWheelRequest.getPlainMessage() == null || cipherWheelRequest.getPlainMessage().isBlank()) {
            // Should return appropriate response code
            return new ResponseEntity<>(new CipherWheelResponse("Invalid Plain Text (Null or Empty)"), HttpStatus.BAD_REQUEST);
        }

        if (cipherWheelRequest.getCipherWheel() == null || cipherWheelRequest.getCipherWheel().isBlank()) {
            // Should return appropriate response code
            return new ResponseEntity<>(new CipherWheelResponse("Invalid Cipher Wheel (Null or Empty)"), HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(cipherWheelService.encodePlainText(cipherWheelRequest), HttpStatus.OK);
    }


}
