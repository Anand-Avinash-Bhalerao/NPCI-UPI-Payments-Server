package com.billion_dollor_company.npciServer.npci.payloads.transaction;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "response")
public class TransactionResDTO {
    private String status;
    private String message;
}

