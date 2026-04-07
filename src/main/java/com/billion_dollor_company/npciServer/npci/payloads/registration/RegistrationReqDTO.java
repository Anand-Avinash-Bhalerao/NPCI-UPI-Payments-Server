package com.billion_dollor_company.npciServer.npci.payloads.registration;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationReqDTO {

    private String encryptedText;

}
