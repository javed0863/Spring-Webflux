package com.javedrpi.webfluxdemo.exception;

/**
 * @author Javed Ameen Shaikh
 * @website <a href="https://www.javedrpi.com">...</a>
 */

public class InputValidationException extends RuntimeException {
    private static final String MSG = "allowed range is 10 - 20";
    private static final int errorCode = 100;
    private final int input;

    public InputValidationException(int input) {
        super(MSG);
        this.input = input;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getInput() {
        return input;
    }
}
