package com.billion_dollor_company.npciServer.npci.service;

import com.billion_dollor_company.npciServer.bank.service.BankService;
import com.billion_dollor_company.npciServer.cryptography.service.CryptographyService;
import com.billion_dollor_company.npciServer.domain.models.BalanceInfo;
import com.billion_dollor_company.npciServer.domain.models.BalanceInquiryInfo;
import org.springframework.stereotype.Service;

@Service
public class NpciService {

    private final BankService bankService;

    private final CryptographyService cryptographyService;

    public NpciService(BankService bankService, CryptographyService cryptographyService) {
        this.bankService = bankService;
        this.cryptographyService = cryptographyService;
    }

    public BalanceInfo balanceInquiry(BalanceInquiryInfo inquiryInfo) {

        String encryptedPassword = inquiryInfo.getCredentials().getEncryptedPassword();
        String upiId = inquiryInfo.getCredentials().getUpiId();

        inquiryInfo.getCredentials().setEncryptedPassword(cryptographyService.decryptAndReEncryptPW(encryptedPassword));

        return bankService.initiateBalanceInquiry(inquiryInfo);
    }
}
