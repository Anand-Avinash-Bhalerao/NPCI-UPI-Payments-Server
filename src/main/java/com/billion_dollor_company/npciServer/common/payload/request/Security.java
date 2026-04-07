package com.billion_dollor_company.npciServer.common.payload.request;

import lombok.Data;

@Data
public class Security {
    private String signature;
    private String encryptionMethod;
}
