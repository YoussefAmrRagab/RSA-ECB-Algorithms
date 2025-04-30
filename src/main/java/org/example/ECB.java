package org.example;

import java.util.List;

import static org.example.Utils.*;

public class ECB {
    private String key;

    ECB() {
        keyGeneration();
    }

    private void keyGeneration() {
        int key = (int) LinearCongruentialGenerator.randomNumber(false) % 128;
        this.key = toBinary(key + "", 8, 10);
        System.out.println("ECB key: " + key + " -> binary: " + this.key);
    }

    public String encrypt(String plainText) {
        String cipherText = "";
        List<Integer> ascii = Utils.textToAscii(plainText);
        for (Integer code : ascii) {
            String binary = toBinary(code + "", 8, 10);
            String cipher = XOR(binary, key);
            cipherText += cipher + " ";
        }
        return cipherText;
    }

    public String decrypt(String cipherText) {
        String plainText = "";
        String[] cipher = cipherText.split(" ");
        for (String code : cipher) {
            String binaryCode = XOR(code, key);
            int decimal = Integer.parseInt(binaryCode, 2);
            char plain = (char) decimal;
            plainText += plain;
        }
        return plainText;
    }
}
