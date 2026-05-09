package com.billion_dollor_company.npciServer.domain.models;

import lombok.Data;

@Data
public class CredentialsInfo {
    private String upiId;
    private String encryptedPassword;
}
