package com.vijaya.cipherwheelwithcopilot.controller;

import com.vijaya.cipherwheelwithcopilot.domain.CipherWheelRequest;
import com.vijaya.cipherwheelwithcopilot.domain.CipherWheelResponse;
import com.vijaya.cipherwheelwithcopilot.service.CipherWheelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CipherWheelControllerTest {

    @InjectMocks
    private CipherWheelController cipherWheelController;
    @Mock
    private CipherWheelService cipherWheelService;

    @Test
    void encodePlainText_WhenKeyIsZero_ShouldReturnBadRequest() {
        // Arrange
        CipherWheelRequest cipherWheelRequest = new CipherWheelRequest();
        cipherWheelRequest.setKey(0);
        cipherWheelRequest.setPlainMessage("Hello World");
        cipherWheelRequest.setCipherWheel("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        // Act
        ResponseEntity<CipherWheelResponse> cipherWheelResponse = cipherWheelController.encodePlainText(cipherWheelRequest);

        // Assert
        assert cipherWheelResponse.getStatusCode().is4xxClientError();
        assert Objects.requireNonNull(cipherWheelResponse.getBody()).getStatus().equals("Key is zero");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "    "})
    void encodePlainText_WhenPlainMessageIsEmptyOrBlank_ShouldReturnBadRequest(String plainMessage) {
        // Arrange
        CipherWheelRequest cipherWheelRequest = new CipherWheelRequest();
        cipherWheelRequest.setKey(10);
        cipherWheelRequest.setPlainMessage(plainMessage);
        cipherWheelRequest.setCipherWheel("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        // Act
        ResponseEntity<CipherWheelResponse> cipherWheelResponse = cipherWheelController.encodePlainText(cipherWheelRequest);

        // Assert
        assert cipherWheelResponse.getStatusCode().is4xxClientError();
        assert Objects.requireNonNull(cipherWheelResponse.getBody()).getStatus().equals("Invalid Plain Text (Null or Empty)");
    }

    @Test
    void encodePlainText_WhenPlainMessageIsNull_ShouldReturnBadRequest() {
        // Arrange
        CipherWheelRequest cipherWheelRequest = new CipherWheelRequest();
        cipherWheelRequest.setKey(10);
        cipherWheelRequest.setPlainMessage(null);
        cipherWheelRequest.setCipherWheel("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        // Act
        ResponseEntity<CipherWheelResponse> cipherWheelResponse = cipherWheelController.encodePlainText(cipherWheelRequest);

        // Assert
        assert cipherWheelResponse.getStatusCode().is4xxClientError();
        assert Objects.requireNonNull(cipherWheelResponse.getBody()).getStatus().equals("Invalid Plain Text (Null or Empty)");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "    "})
    void encodePlainText_WhenCipherWheelIsBlankOrEmpty_ShouldReturnBadRequest(String cipherWheel) {
        // Arrange
        CipherWheelRequest cipherWheelRequest = new CipherWheelRequest();
        cipherWheelRequest.setKey(10);
        cipherWheelRequest.setPlainMessage("Hello");
        cipherWheelRequest.setCipherWheel(cipherWheel);

        // Act
        ResponseEntity<CipherWheelResponse> cipherWheelResponse = cipherWheelController.encodePlainText(cipherWheelRequest);

        // Assert
        assert cipherWheelResponse.getStatusCode().is4xxClientError();
        assert Objects.requireNonNull(cipherWheelResponse.getBody()).getStatus().equals("Invalid Cipher Wheel (Null or Empty)");
    }

    @Test
    void encodePlainText_WhenCipherWheelIsNull_ShouldReturnBadRequest() {
        // Arrange
        CipherWheelRequest cipherWheelRequest = new CipherWheelRequest();
        cipherWheelRequest.setKey(10);
        cipherWheelRequest.setPlainMessage("Hello");
        cipherWheelRequest.setCipherWheel(null);

        // Act
        ResponseEntity<CipherWheelResponse> cipherWheelResponse = cipherWheelController.encodePlainText(cipherWheelRequest);

        // Assert
        assert cipherWheelResponse.getStatusCode().is4xxClientError();
        assert Objects.requireNonNull(cipherWheelResponse.getBody()).getStatus().equals("Invalid Cipher Wheel (Null or Empty)");

    }

    @Test
    void encodePlainText_WhenRequestValid_ShouldReturnSuccess() {
        // Arrange
        CipherWheelRequest cipherWheelRequest = new CipherWheelRequest();
        cipherWheelRequest.setKey(10);
        cipherWheelRequest.setPlainMessage("Hello");
        cipherWheelRequest.setCipherWheel("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        // Act
        ResponseEntity<CipherWheelResponse> cipherWheelResponse = cipherWheelController.encodePlainText(cipherWheelRequest);

        // Assert
        assert cipherWheelResponse.getStatusCode().is2xxSuccessful();
        verify(cipherWheelService, times(1)).encodePlainText(cipherWheelRequest);
    }
}
