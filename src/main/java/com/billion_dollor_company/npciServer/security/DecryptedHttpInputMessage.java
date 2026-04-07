package com.billion_dollor_company.npciServer.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class DecryptedHttpInputMessage implements HttpInputMessage {

    private final HttpHeaders headers;
    private final InputStream body;

    public DecryptedHttpInputMessage(HttpHeaders headers, String decryptedJson) {
        this.headers = headers;
        this.body = new ByteArrayInputStream(
                decryptedJson.getBytes(StandardCharsets.UTF_8)
        );
    }

    @Override
    public InputStream getBody() {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders newHeaders = new HttpHeaders();
        newHeaders.putAll(this.headers);

        newHeaders.setContentType(MediaType.APPLICATION_JSON);

        return newHeaders;
    }
}