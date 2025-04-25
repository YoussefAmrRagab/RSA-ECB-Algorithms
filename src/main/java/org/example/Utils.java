package org.example;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<Integer> textToAscii(String plainText) {
        List<Integer> ascii = new ArrayList<>();
        for (char c : plainText.toCharArray()) {
//            System.out.println("ascii: " + (int) c);
            ascii.add((int) c);
        }
        return ascii;
    }
}
