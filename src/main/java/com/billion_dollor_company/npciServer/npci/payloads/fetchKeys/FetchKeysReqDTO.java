package com.billion_dollor_company.npciServer.npci.payloads.fetchKeys;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JacksonXmlRootElement(localName = "response")
public class FetchKeysReqDTO {
}
