package com.billion_dollor_company.npciServer.npci.payloads.registration;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "response")
@Builder
public class RegistrationResDTO {
    public String status;
    public String message;
}
