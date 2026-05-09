package com.billion_dollor_company.npciServer.npci.payload;

import lombok.Data;

@Data
public class Credentials {
    private String type;
    private String encryptedPassword;
}
