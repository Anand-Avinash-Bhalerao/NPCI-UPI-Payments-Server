package com.billion_dollor_company.npciServer.domain.models;

import lombok.Data;

@Data
public class BalanceInfo {
    private String upiId;
    private String accountHolderName;
    private double balanceAmount;
}
