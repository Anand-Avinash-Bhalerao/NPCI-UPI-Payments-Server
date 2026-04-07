package com.billion_dollor_company.npciServer.npci.payloads.checkBalance;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "response")
public class BalanceResDTO {
    private String status;
    private String message;
}
