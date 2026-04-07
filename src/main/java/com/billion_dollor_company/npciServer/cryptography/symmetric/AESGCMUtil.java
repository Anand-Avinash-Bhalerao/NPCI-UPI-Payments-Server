package com.billion_dollor_company.npciServer.cryptography.symmetric;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AESGCMUtil {

    private static final String AES = "AES";
    private static final String AES_GCM = "AES/GCM/NoPadding";
    private static final int IV_LENGTH = 12; // recommended 12 bytes
    private static final int TAG_LENGTH = 128; // in bits (16 bytes)

    // ------------------ ENCRYPT ------------------
    public static EncryptionResult encrypt(String plainText) throws Exception {

        // Generate Key
        KeyGenerator keyGen = KeyGenerator.getInstance(AES);
        keyGen.init(256); // or 128 if restrictions apply
        SecretKey key = keyGen.generateKey();

        // Generate IV
        byte[] iv = new byte[IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        Cipher cipher = Cipher.getInstance(AES_GCM);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH, iv);

        cipher.init(Cipher.ENCRYPT_MODE, key, spec);

        byte[] cipherTextWithTag = cipher.doFinal(plainText.getBytes());

        return new EncryptionResult(
                Base64.getEncoder().encodeToString(cipherTextWithTag),
                Base64.getEncoder().encodeToString(iv),
                Base64.getEncoder().encodeToString(key.getEncoded())
        );
    }

    // ------------------ DECRYPT ------------------
    public static String decrypt(String base64CipherText, String base64IV, String base64Key) throws Exception {

        byte[] cipherTextWithTag = Base64.getDecoder().decode(base64CipherText);
        byte[] iv = Base64.getDecoder().decode(base64IV);
        byte[] keyBytes = Base64.getDecoder().decode(base64Key);

        SecretKey key = new SecretKeySpec(keyBytes, AES);

        Cipher cipher = Cipher.getInstance(AES_GCM);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH, iv);

        cipher.init(Cipher.DECRYPT_MODE, key, spec);

        byte[] plainText = cipher.doFinal(cipherTextWithTag);

        return new String(plainText);
    }

    // ------------------ RESULT CLASS ------------------
    public static class EncryptionResult {
        private final String cipherText;
        private final String iv;
        private final String key;

        public EncryptionResult(String cipherText, String iv, String key) {
            this.cipherText = cipherText;
            this.iv = iv;
            this.key = key;
        }

        public String getCipherText() { return cipherText; }
        public String getIv() { return iv; }
        public String getKey() { return key; }

        @Override
        public String toString() {
            return "EncryptionResult{" +
                    "cipherText='" + cipherText + '\'' +
                    ", iv='" + iv + '\'' +
                    ", key='" + key + '\'' +
                    '}';
        }
    }
}