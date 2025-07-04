package org.example;

import java.util.HashSet;
import java.util.Set;

public class LinearCongruentialGenerator {
    private static final Set<Long> generatedNumbers = new HashSet<>();
    private static long x = 0;

    /// `shouldBePrime` is used to generate either prime or non-prime number.
    public static long randomNumber(boolean shouldBePrime) {
        long multiplier = 15L;
        long x0 = System.currentTimeMillis() % 10_000;
        long increment = 1L;
        long modulus = 10_000L;
        long tries = 1;
        while (true) {
            if (tries == modulus) {
                // Reset the number of tries & x
                // To regenerate new random number from new seed value
                x0 = System.currentTimeMillis() % 10_000;
                x = 0;
                tries = 1;
            }
            long rand = LCG(multiplier, x0, increment, modulus);

            // check if a random number is prime and not already generated
            if ((isPrime(rand) == shouldBePrime) && !generatedNumbers.contains(rand)) {
                generatedNumbers.add(rand);
                return rand;
            } else {
                tries++;
            }
        }
    }

    /// Checks if a number is prime or not.
    private static boolean isPrime(long n) {
        if (n < 2) return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    /// * Formula: Xₙ₊₁ = (aXₙ + c) mod m
    /// * Modulus: 0 < m
    /// * Multiplier: 0 < a < m
    /// * Increment: 0 <= c < m
    /// * Seed (start value): 0 <= X₀ < m
    private static long LCG(long multiplier, long x0, long increment, long modulus) {
        if (x == 0) {
            x = ((multiplier * x0) + increment) % modulus;
        } else {
            x = ((multiplier * x) + increment) % modulus;
        }
        return x;
    }
}
