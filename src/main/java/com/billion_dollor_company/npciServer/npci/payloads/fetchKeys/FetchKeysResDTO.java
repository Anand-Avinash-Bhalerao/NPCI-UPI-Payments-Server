package com.billion_dollor_company.npciServer.npci.payloads.fetchKeys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FetchKeysResDTO {
    private String status;
    private String message;
}
