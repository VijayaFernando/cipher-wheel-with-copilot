package com.vijaya.cipherwheelwithcopilot.service;

import com.vijaya.cipherwheelwithcopilot.domain.CipherWheelRequest;
import com.vijaya.cipherwheelwithcopilot.domain.CipherWheelResponse;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CipherWheelServiceTest {

    @InjectMocks
    private CipherWheelService cipherWheelService;

    @ParameterizedTest
    @CsvSource({
            "Hello World!, 3, Khoor Zruog!",
            "OpenAI, 5, TujsFN"
    })
    void encodePlainText_WithValidRequestUsingParameterizedTest(String plainText, int key, String expectedEncodedMessage) {
        // Arrange
        CipherWheelRequest cipherWheelRequest = new CipherWheelRequest();
        cipherWheelRequest.setCipherWheel("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        cipherWheelRequest.setKey(key);
        cipherWheelRequest.setPlainMessage(plainText);

        // Act
        CipherWheelResponse cipherWheelResponse = cipherWheelService.encodePlainText(cipherWheelRequest);

        // Assert
        assertEquals(expectedEncodedMessage, cipherWheelResponse.getEncodedMessage());
        assertEquals("SUCCESS", cipherWheelResponse.getStatus());
    }

}
