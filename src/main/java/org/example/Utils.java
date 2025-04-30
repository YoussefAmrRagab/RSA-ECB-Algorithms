package org.example;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    static List<Integer> textToAscii(String plainText) {
        List<Integer> ascii = new ArrayList<>();
        for (char c : plainText.toCharArray()) {
//            System.out.println("ascii: " + (int) c);
            ascii.add((int) c);
        }
        return ascii;
    }

    static String XOR(String... binaries) {
        String result = binaries[0];
        for (int i = 1; i < binaries.length; i++) {
            String temp = result;
            result = "";
            for (int j = 0; j < binaries[i].length(); j++) {
                result += binaries[i].charAt(j) == temp.charAt(j) ? '0' : '1';
            }
        }
        return result;
    }

    static String toBinary(String s, int length, int base) {
        String binary = "";
        switch (base) {
            case 16:
                for (int i = 0; i < s.length(); i++) {
                    int hexNumber = Integer.parseInt(s.charAt(i) + "", 16);
                    String binaryNumber = Integer.toBinaryString(hexNumber);
                    String paddedBinary = padding(binaryNumber, length, true);
                    binary += paddedBinary;
                }
                break;
            case 10:
                int decimalNumber = Integer.parseInt(s);
                String binaryNumber = Integer.toBinaryString(decimalNumber);
                String paddedBinary = padding(binaryNumber, length, true);
                binary += paddedBinary;
                break;
        }
        return binary;
    }

    static String padding(String s, int length, boolean leftPadding) {
        String padded;
        if (leftPadding) {
            padded = String.format("%" + length + "s", s);
        } else {
            padded = String.format("%-" + length + "s", s);
        }
        return padded.replace(" ", "0");
    }
}