package com.billion_dollor_company.npciServer.cryptography.utils;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class EncryptionManager {
    private final String keyName;
    private PublicKey publicKey;

    public EncryptionManager(String key, String name) {
        this.keyName = name;
        initWithStrings(key);
    }

    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String encodedStr) {
        return Base64.getDecoder().decode(encodedStr);
    }
    // this function is used to initialize the public key.
    private void initWithStrings(String key) {
        try {
            X509EncodedKeySpec keySpecPublic = new X509EncodedKeySpec(decode(key));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            publicKey = keyFactory.generatePublic(keySpecPublic);
        } catch (Exception ignored) {
        }
    }

    public void printKeys() {
        System.out.println("The public key named:'" + keyName + "'is :" + encode(publicKey.getEncoded()));
    }

    public String getEncryptedMessage(String message) {
        try {

            byte[] messageToBytes = message.getBytes();
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] encryptedBytes = cipher.doFinal(messageToBytes);
            return encode(encryptedBytes);
        } catch (Exception e) {
            return null;
        }

    }
}
