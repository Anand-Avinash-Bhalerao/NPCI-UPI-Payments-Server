package com.billion_dollor_company.npciServer.bank.service;

import com.billion_dollor_company.npciServer.common.constants.Constants;
import com.billion_dollor_company.npciServer.common.payload.request.BaseReqDTO;
import com.billion_dollor_company.npciServer.common.payload.response.BaseResDTO;
import com.billion_dollor_company.npciServer.domain.models.BalanceInfo;
import com.billion_dollor_company.npciServer.domain.models.BalanceInquiryInfo;
import com.billion_dollor_company.npciServer.domain.models.CredentialsInfo;
import com.billion_dollor_company.npciServer.npci.payload.AccountHolder;
import com.billion_dollor_company.npciServer.npci.payload.Credentials;
import com.billion_dollor_company.npciServer.npci.payload.checkBalance.request.CheckBalanceReqBody;
import com.billion_dollor_company.npciServer.npci.payload.checkBalance.request.CheckBalanceReqDTO;
import com.billion_dollor_company.npciServer.npci.payload.checkBalance.response.CheckBalanceResDTO;
import com.billion_dollor_company.npciServer.npci.payload.checkBalance.response.CheckBalanceResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BankService {

    private final RestTemplate restTemplate;

    public BankService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public BalanceInfo initiateBalanceInquiry(BalanceInquiryInfo inquiryInfo) {
        String checkBalanceURL = Constants.Servers.BankServer.getCheckBalanceURL();

        BaseReqDTO<CheckBalanceReqBody> request = new BaseReqDTO<>();
        CheckBalanceReqBody reqBody = new CheckBalanceReqBody();
        Credentials credentials = new Credentials();
        credentials.setEncryptedPassword(inquiryInfo.getCredentials().getEncryptedPassword());
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setUpiId(inquiryInfo.getCredentials().getUpiId());

        reqBody.setCredentials(credentials);
        reqBody.setAccountHolder(accountHolder);
        request.setBody(reqBody);

        BaseResDTO response =  restTemplate.postForEntity(checkBalanceURL, request, BaseResDTO.class).getBody();
        CheckBalanceResponseBody responseBody = (CheckBalanceResponseBody) response.getBody();

        BalanceInfo balanceInfo = new BalanceInfo();
        balanceInfo.setBalanceAmount(responseBody.getBalance());
        return balanceInfo;
    }
}
