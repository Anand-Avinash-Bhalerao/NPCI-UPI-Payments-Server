package com.billion_dollor_company.npciServer.cryptography.service;

public interface CryptographyService {
    public String decryptAndReEncryptPW(String encryptedPassword);

    public String decryptAndReEncryptChallenge(String encryptedText);
}
