package com.billion_dollor_company.npciServer.npci.payloads.transaction;

import lombok.Data;

@Data
public class TransactionReqDTO {
    private String payeeUpiID;
    private String payerUpiID;
    private String payerAccountNo;
    private String payerBankName;
    private String encryptedPassword;
    private String amountToTransfer;
}