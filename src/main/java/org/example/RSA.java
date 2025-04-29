package org.example;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;


public class RSA {

    private BigInteger publicKey; // for encryption
    private BigInteger privateKey; // for decryption
    private BigInteger modulus;

    RSA() {
        keyGeneration();
    }

    private void keyGeneration() {
        // Step 1: Generate two prime numbers & calculate n
        long p = LinearCongruentialGenerator.randomNumber(true);
        long q = LinearCongruentialGenerator.randomNumber(true);
        System.out.println("p (prime number): " + p + "\nq (prime number): " + q);
        long n = p * q;
        modulus = new BigInteger(n + "");
        System.out.println("n (modulus) : " + n);

        // Step 2. Find θ(N)
        long phi_n = (p - 1) * (q - 1);
        BigInteger phi_mod = new BigInteger(phi_n + "");
        System.out.println("phi_n: " + phi_n);

        // Step 3. Generate an encryption key
        long GCD;
        long e;
        Random rand = new Random();
        do {
            // 1 < e < phi_n → range: 2 to phi_n - 1
            // public key
            e = rand.nextLong(phi_n - 2) + 2;
            GCD = greatestCommonDivisor(e, phi_n);
        } while (GCD != 1);
        System.out.println("e (encryption key): " + e);
        publicKey = new BigInteger(e + "");

        // Step 4. Generate a decryption key
        privateKey = publicKey.modInverse(phi_mod);
        System.out.println("d (decryption key): " + privateKey);


        // Step 5. Set public key and private key
        System.out.println("public key: " + publicKey);
        System.out.println("private key: " + privateKey);
    }

    public String encrypt(String plainText) {
        List<Integer> ascii = Utils.textToAscii(plainText);
        String cipherText = "";
        for (Integer value : ascii) {
            BigInteger msg = new BigInteger(value + "");
            BigInteger encryption = msg.modPow(publicKey, modulus);
//            System.out.println("encrypted: " + encryption);
            cipherText += encryption.longValue() + " ";
        }

        return cipherText;
    }

    public String decrypt(String cipherText) {
        String[] ascii = cipherText.split(" ");
        String plainText = "";

        for (String value : ascii) {
            BigInteger cipher = new BigInteger(value);
            BigInteger decrypted = cipher.modPow(privateKey, modulus);
//            System.out.println("decrypted: " + decrypted);
            plainText += ((char) decrypted.intValue());
        }

        return plainText;
    }

    private long greatestCommonDivisor(long m, long n) {
        // Example:
        //  greatestCommonDivisor(48, 18)
        //      → 48 % 18 = 12 → gcd(18, 12)
        //      → 18 % 12 = 6  → gcd(12, 6)
        //      → 12 % 6 = 0   → return 6
        while (n != 0) {
            long temp = n;
            n = m % n;
            m = temp;
        }
        return m;
    }

}