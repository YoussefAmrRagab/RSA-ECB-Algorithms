package org.example;

import java.util.List;

public class ECB {
    private static long key;

    public ECB() {
        keyGeneration();
    }

    private void keyGeneration() {
        key = LinearCongruentialGenerator.randomNumber(false);
        System.out.println("key: " + key);
    }

    public String encrypt(String plainText) {
        String cipherText = "";
        List<Integer> ascii = Utils.textToAscii(plainText);
        for (Integer code : ascii) {
            int cipher = (int) (code ^ key);
            cipherText += cipher + " ";
        }
        return cipherText;
    }

    public String decrypt(String cipherText) {
        String plainText = "";
        String[] cipher = cipherText.split(" ");
        for (String code : cipher) {
            int plain = (int) (Integer.parseInt(code) ^ key);
            plainText += (char) plain;
        }
        return plainText;
    }

}
