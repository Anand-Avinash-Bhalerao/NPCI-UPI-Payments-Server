package com.billion_dollor_company.npciServer.npci.payloads.checkBalance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceResDTO {
    private String status;
    private String message;
}
