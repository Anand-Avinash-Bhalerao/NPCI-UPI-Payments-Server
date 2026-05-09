package com.billion_dollor_company.npciServer.cryptography.service.impl;

import com.billion_dollor_company.npciServer.cryptography.service.CryptographyService;
import com.billion_dollor_company.npciServer.common.constants.Constants;
import com.billion_dollor_company.npciServer.cryptography.utils.DecryptionManager;
import com.billion_dollor_company.npciServer.cryptography.utils.EncryptionManager;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

@Service
public class CryptographyServiceImpl implements CryptographyService {

    @Override
    public String decryptAndReEncryptPW(String encryptedPassword) {

        DecryptionManager decryptionManager = new DecryptionManager(Constants.Keys.NPCI_PRIVATE_KEY, "NPCI private key");

        // This is the password entered by the user. example -123456
        String originalPassword = decryptionManager.getDecryptedMessage(encryptedPassword);

        System.out.println("Decrypted password at NPCI is : " + originalPassword);

        // now encrypt the password with Bank's public key.
        EncryptionManager encryptionManager = new EncryptionManager(Constants.Keys.BANK_PUBLIC_KEY, "Bank's private key");

        // This is the password encrypted with the bank's public key.
        return encryptionManager.getEncryptedMessage(originalPassword);
    }

    @Override
    public String decryptAndReEncryptChallenge(String encryptedText) {
        System.out.println("The encrypted text is: " + encryptedText);
        try {
            String decryptedText = decryptText(encryptedText);
            System.out.println("The decrypted text is: " + decryptedText);
            if (decryptedText.length() < 10) throw new Exception();
            String padding = "00000";
            decryptedText = padding + decryptedText + padding;
            System.out.println("After adding the padding the text is: " + decryptedText);
            return encryptText(decryptedText);
        } catch (Exception e) {
        }
        return null;
    }


    private SecretKey getSecretKey() throws Exception {
        String password = "mD7!nA4q8Jr@2Zt9&Ls#";
        byte[] keyBytes = password.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        keyBytes = sha.digest(keyBytes);
        keyBytes = java.util.Arrays.copyOf(keyBytes, 16); // Use only first 128 bits (16 bytes)
        return new SecretKeySpec(keyBytes, "AES");
    }

    public String encryptText(String text) throws Exception {
        SecretKey secretKey = getSecretKey();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decryptText(String encryptedText) throws Exception {
        SecretKey secretKey = getSecretKey();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }


}
