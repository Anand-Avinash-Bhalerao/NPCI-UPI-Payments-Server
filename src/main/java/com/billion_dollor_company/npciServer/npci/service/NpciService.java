package com.billion_dollor_company.npciServer.npci.service;

import com.billion_dollor_company.npciServer.domain.models.BalanceInfo;
import com.billion_dollor_company.npciServer.domain.models.BalanceInquiryInfo;
import com.billion_dollor_company.npciServer.payloads.fetchKeys.FetchKeysResDTO;
import com.billion_dollor_company.npciServer.payloads.registration.RegistrationReqDTO;
import com.billion_dollor_company.npciServer.payloads.registration.RegistrationResDTO;
import com.billion_dollor_company.npciServer.payloads.transaction.TransactionReqDTO;
import com.billion_dollor_company.npciServer.payloads.transaction.TransactionResDTO;

public interface NpciService {
    TransactionResDTO initiateTransaction(TransactionReqDTO requestInfo);

    BalanceInfo getAccountBalance(BalanceInquiryInfo requestInfo);

    RegistrationResDTO registration(RegistrationReqDTO requestInfo);

    FetchKeysResDTO fetchKeys();
}
