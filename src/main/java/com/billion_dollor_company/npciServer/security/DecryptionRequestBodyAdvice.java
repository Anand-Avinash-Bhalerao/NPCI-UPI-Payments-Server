package com.billion_dollor_company.npciServer.security;


import com.billion_dollor_company.npciServer.common.constants.Constants;
import com.billion_dollor_company.npciServer.common.payload.EncryptedReqDTO;
import com.billion_dollor_company.npciServer.cryptography.symmetric.AES;
import com.billion_dollor_company.npciServer.cryptography.utils.DecryptionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class DecryptionRequestBodyAdvice extends RequestBodyAdviceAdapter {

    private final Validator validator;

    @Autowired
    public DecryptionRequestBodyAdvice(Validator validator) {
        this.validator = validator;
    }

    /**
     * Decide when to apply this advice.
     * Here we apply to ALL @RequestBody. You can restrict if needed.
     */
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return true; // or restrict using annotation / package / DTO type
    }

    /**
     * Intercepts BEFORE Spring converts JSON → DTO
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage,
                                           MethodParameter parameter,
                                           Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType)
            throws IOException {

        // 1. Read raw body
        String rawBody = new BufferedReader(
                new InputStreamReader(inputMessage.getBody(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining());

        if (rawBody == null || rawBody.isBlank()) {
            throw new HttpMessageNotReadableException("Empty request body", inputMessage);
        }

        // 2. Convert to EncryptedReqDTO
        EncryptedReqDTO encryptedReq;
        try {
            XmlMapper xmlMapper = new XmlMapper();
            encryptedReq = xmlMapper.readValue(rawBody, EncryptedReqDTO.class);
        } catch (Exception e) {
            throw new HttpMessageNotReadableException("Invalid encrypted request format", e);
        }

        // 3. Validate (IMPORTANT since controller won’t see EncryptedReqDTO)
        Set<ConstraintViolation<EncryptedReqDTO>> violations = validator.validate(encryptedReq);
        if (!violations.isEmpty()) {
            String errorMsg = violations.stream()
                    .map(v -> v.getPropertyPath() + " " + v.getMessage())
                    .collect(Collectors.joining(", "));
            throw new HttpMessageNotReadableException("Validation failed: " + errorMsg, inputMessage);
        }

        // 4. Decrypt
        String decryptedJson;
        try {
            decryptedJson = decrypt(
                    encryptedReq.getEncryptedData(),
                    encryptedReq.getEncryptedKey()
            );
        } catch (Exception e) {
            throw new HttpMessageNotReadableException("Decryption failed",e, inputMessage);
        }

        // 5. Replace body with decrypted JSON
        return new DecryptedHttpInputMessage(inputMessage.getHeaders(), decryptedJson);
    }

    /**
     * Your decryption logic (stub for now)
     */
    private String decrypt(String encryptedData, String encryptedKey) {

        DecryptionManager decryptionManager = new DecryptionManager(Constants.Keys.NPCI_PRIVATE_KEY, "");

        String decryptedKey = decryptionManager.getDecryptedMessage(encryptedKey);

        String decrypted = AES.decrypt(encryptedData, decryptedKey);

        System.out.println(decrypted);

        return decrypted;
    }
}