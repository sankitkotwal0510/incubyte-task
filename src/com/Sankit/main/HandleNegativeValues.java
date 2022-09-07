package com.ajinz.main;

public class HandleNegativeValues {
    public static class NegativesNotAllowed extends RuntimeException {
        public NegativesNotAllowed(String s) {
            super(s);
        }
    }

    public static void throwExceptionIfNegativeExists(String negative_numbers_str) {
        if (negative_numbers_str != "") {
            throw new NegativesNotAllowed("Negatives Not Allowed. Numbers are: " + negative_numbers_str);
        }
    }

}