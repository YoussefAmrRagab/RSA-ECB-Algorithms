package org.example;

import java.util.List;

public class ECB {
    private String key;

    ECB() {
        keyGeneration();
    }

    private void keyGeneration() {
        int key = (int) LinearCongruentialGenerator.randomNumber(false) % 128;
        this.key = toBinary(key);
        System.out.println("key: " + key + " binary: " + this.key);
    }

    public String encrypt(String plainText) {
        String cipherText = "";
        List<Integer> ascii = Utils.textToAscii(plainText);
        for (Integer code : ascii) {
            String binary = toBinary(code);
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

    private String toBinary(int num) {
        String binaryNumber = Integer.toBinaryString(num);
        return String.format("%8s", binaryNumber).replace(" ", "0");
    }

    private String XOR(String s1, String s2) {
        String result = "";
        for (int i = 0; i < s1.length(); i++) {
            result += s1.charAt(i) == s2.charAt(i) ? '0' : '1';
        }
        return result;
    }
}
