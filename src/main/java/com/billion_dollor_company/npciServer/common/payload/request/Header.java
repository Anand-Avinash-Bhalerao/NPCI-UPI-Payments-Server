package com.billion_dollor_company.npciServer.common.payload.request;

import lombok.Data;

@Data
public class Header {
    private String messageId;
    private String timestamp;
    private String source;
    private String channel;
    private String nonce;
    private String requestType;
}
