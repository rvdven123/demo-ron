package com.rabo.demoron;

public class FatalDemoRonException extends RuntimeException {

    public FatalDemoRonException(String message) {
        super(message);
    }

    public FatalDemoRonException(String message, Throwable cause) {
        super(message, cause);
    }
}
