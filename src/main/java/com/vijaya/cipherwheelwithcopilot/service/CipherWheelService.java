package com.vijaya.cipherwheelwithcopilot.service;

import com.vijaya.cipherwheelwithcopilot.domain.CipherWheelRequest;
import com.vijaya.cipherwheelwithcopilot.domain.CipherWheelResponse;
import org.springframework.stereotype.Service;

@Service
public class CipherWheelService {

    //Check if it is a letter (A-Z or a-z)
    //If it is not a letter, append the character as is to the encoded message
    //If it is a letter, perform the encryption process.
    public CipherWheelResponse encodePlainText(CipherWheelRequest cipherWheelRequest) {
        StringBuilder encodedMessageText = new StringBuilder();

        for (char ch : cipherWheelRequest.getPlainMessage().toCharArray()) {

            if (Character.isLetter(ch)) {
                char encodedChar = encryptionProcess(ch, cipherWheelRequest.getKey(), cipherWheelRequest.getCipherWheel());
                encodedMessageText.append(encodedChar);

            } else {
                encodedMessageText.append(ch);
            }
        }

        CipherWheelResponse cipherWheelResponse = new CipherWheelResponse();
        cipherWheelResponse.setEncodedMessage(encodedMessageText.toString());
        cipherWheelResponse.setStatus("SUCCESS");

        return cipherWheelResponse;

    }

    // Determine the letter's position in the alphabet (0-25) by converting it to uppercase and subtracting the ASCII value of 'A'.
    // Add the key to the letter's position.
    // Handle wrapping around the alphabet by taking the modulo 26 of the result.
    // Convert the resulting position back to a letter by adding the ASCII value of 'A'.
    // Append the encrypted letter to the encoded message.
    private char encryptionProcess(char ch, int key, String cipherWheel) {
        char upperCh = Character.toUpperCase(ch);
        int position = upperCh - 'A'; //determine the letter position

        int encodedPosition = (position + key) % cipherWheel.length(); //or 26 for english alphabet
        char encodedChar = cipherWheel.charAt(encodedPosition);

        return Character.isLowerCase(ch) ? Character.toLowerCase(encodedChar) : encodedChar;
    }
}
