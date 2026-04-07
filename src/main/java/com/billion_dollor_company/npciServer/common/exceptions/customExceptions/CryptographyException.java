package com.billion_dollor_company.npciServer.common.exceptions.customExceptions;

public class CryptographyException extends RuntimeException {
    String message;

    public CryptographyException(String message) {
        super(message);
        this.message = message;
    }
}