package com.billion_dollor_company.npciServer.bank.service;

import com.billion_dollor_company.npciServer.domain.models.BalanceInfo;
import com.billion_dollor_company.npciServer.domain.models.BalanceInquiryInfo;
import com.billion_dollor_company.npciServer.payloads.transaction.TransactionReqDTO;
import com.billion_dollor_company.npciServer.payloads.transaction.TransactionResDTO;

public interface BankApiService {
    public TransactionResDTO initiateTransaction(TransactionReqDTO requestInfo);
    BalanceInfo getAccountBalance(BalanceInquiryInfo requestInfo);

}
