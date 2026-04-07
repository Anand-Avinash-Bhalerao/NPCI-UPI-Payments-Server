package com.billion_dollor_company.npciServer.npci.payloads.fetchKeys;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "response")
public class FetchKeysResDTO {
    private String status;
    private String message;
}
