package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.example.Utils.*;

public class SHA1 {

    public String hash(String plainText) {
        String h0 = "67452301";
        String h1 = "EFCDAB89";
        String h2 = "98BADCFE";
        String h3 = "10325476";
        String h4 = "C3D2E1F0";

        String A = toBinary(h0, 4, 16);
        String B = toBinary(h1, 4, 16);
        String C = toBinary(h2, 4, 16);
        String D = toBinary(h3, 4, 16);
        String E = toBinary(h4, 4, 16);

        List<Integer> ascii = Utils.textToAscii(plainText);
        String binaryMessage = "";
        for (Integer code : ascii) {
            binaryMessage += toBinary(code + "", 8, 10);
        }
        int msgLength = binaryMessage.length();
        binaryMessage += "1";
        binaryMessage = padding(binaryMessage, 448, false);
        String binaryMsgLength = toBinary(msgLength + "", 64, 10);
        binaryMessage += binaryMsgLength;

        List<String> w = new ArrayList<>();
        for (int i = 0; i < 512; i += 32) {
            w.add(binaryMessage.substring(i, i + 32));
        }

        for (int i = 16; i <= 79; i++) {
            String XOR = XOR(w.get(i - 3), w.get(i - 8), w.get(i - 14), w.get(i - 16));
            String wi = leftRotate(XOR, 1);
            w.add(wi);
        }

        for (int i = 0; i < 80; i++) {
            String temp = iterate(i, A, B, C, D, E, w.get(i));
            E = D;
            D = C;
            C = leftRotate(B, 30);
            B = A;
            A = temp;
        }

        h0 = ADD(toBinary(h0, 4, 16), A);
        h1 = ADD(toBinary(h1, 4, 16), B);
        h2 = ADD(toBinary(h2, 4, 16), C);
        h3 = ADD(toBinary(h3, 4, 16), D);
        h4 = ADD(toBinary(h4, 4, 16), E);
        String hash = h0 + h1 + h2 + h3 + h4;

        return binaryToHex(hash);
    }

    private String iterate(int i, String A, String B, String C, String D, String E, String W) {
        String leftShiftA = leftRotate(A, 5);
        if (i <= 19) {
            String K = "5A827999";
            String binaryK = toBinary(K, 4, 16);
            String fun = OR(AND(B, C), AND(NOT(B), D));
            return ADD(leftShiftA, fun, E, W, binaryK);
        } else if (i <= 39) {
            String K = "6ED9EBA1";
            String binaryK = toBinary(K, 4, 16);
            String fun = XOR(B, C, D);
            return ADD(leftShiftA, fun, E, W, binaryK);
        } else if (i <= 59) {
            String K = "8F1BBCDC";
            String binaryK = toBinary(K, 4, 16);
            String fun = OR(AND(B, C), AND(B, D), AND(C, D));
            return ADD(leftShiftA, fun, E, W, binaryK);
        } else {
            String K = "CA62C1D6";
            String binaryK = toBinary(K, 4, 16);
            String fun = XOR(B, C, D);
            return ADD(leftShiftA, fun, E, W, binaryK);
        }
    }

    private String AND(String... binaries) {
        String result = binaries[0];
        for (int i = 1; i < binaries.length; i++) {
            String temp = result;
            result = "";
            for (int j = 0; j < binaries[i].length(); j++) {
                result += binaries[i].charAt(j) == temp.charAt(j) && binaries[i].charAt(j) == '1' ? '1' : '0';
            }
        }
        return result;
    }

    private String OR(String... binaries) {
        String result = binaries[0];
        for (int i = 1; i < binaries.length; i++) {
            String temp = result;
            result = "";
            for (int j = 0; j < binaries[i].length(); j++) {
                result += binaries[i].charAt(j) == temp.charAt(j) && binaries[i].charAt(j) == '0' ? '0' : '1';
            }
        }
        return result;
    }

    private String ADD(String... binaries) {
        String result = binaries[0];
        for (int i = 1; i < binaries.length; i++) {
            BigInteger num1 = new BigInteger(result, 2);
            BigInteger num2 = new BigInteger(binaries[i], 2);
            BigInteger sum = num1.add(num2);
            result = sum.toString(2);
        }
        if (result.length() < 32) {
            return padding(result, 32, true);
        } else if (result.length() > 32) {
            return result.substring(result.length() - 32);
        }
        return result;
    }

    private String NOT(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            result += s.charAt(i) == '1' ? '0' : '1';
        }
        return result;
    }

    private String leftRotate(String s, int shifting) {
        // let shift = 2
        // s = "hello"
        String shifted = s.substring(shifting); // "llo"
        String shift = s.substring(0, shifting); // "he"
        return shifted + shift; // "llohe"
    }

    private String binaryToHex(String binaries) {
        String hex = "";
        for (int i = 0; i < binaries.length(); i += 4) {
            String binary = binaries.substring(i, i + 4);
            int decimal = Integer.parseInt(binary, 2);
            hex += Integer.toHexString(decimal);
        }
        return hex;
    }
}
