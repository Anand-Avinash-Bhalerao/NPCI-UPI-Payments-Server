package com.billion_dollor_company.npciServer.npci.controller;

import com.billion_dollor_company.npciServer.common.payload.request.BaseReqDTO;
import com.billion_dollor_company.npciServer.domain.models.BalanceInfo;
import com.billion_dollor_company.npciServer.domain.models.BalanceInquiryInfo;
import com.billion_dollor_company.npciServer.domain.models.CredentialsInfo;
import com.billion_dollor_company.npciServer.npci.mapper.NpciMapper;
import com.billion_dollor_company.npciServer.npci.payload.checkBalance.request.CheckBalanceReqBody;
import com.billion_dollor_company.npciServer.npci.payload.checkBalance.response.CheckBalanceResDTO;
import com.billion_dollor_company.npciServer.npci.payload.checkBalance.response.CheckBalanceResponseBody;
import com.billion_dollor_company.npciServer.npci.service.NpciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for NPCI
 */
@RestController
@RequestMapping(
        value = "/npci",
        produces = {"application/json"}
)
public class NpciController {

    private final NpciService npciService;

    private final NpciMapper npciMapper;

    @Autowired
    public NpciController(NpciService npciService, NpciMapper npciMapper) {
        this.npciService = npciService;
        this.npciMapper = npciMapper;
    }

    @PostMapping("/checkBalance")
    public ResponseEntity<CheckBalanceResDTO> getAccountBalance(@RequestBody BaseReqDTO<CheckBalanceReqBody> request) {

        CheckBalanceReqBody reqBody = request.getBody();

        BalanceInquiryInfo inquiryInfo = new BalanceInquiryInfo();
        CredentialsInfo credentialsInfo = new CredentialsInfo();
        credentialsInfo.setEncryptedPassword(reqBody.getCredentials().getEncryptedPassword());
        credentialsInfo.setUpiId(reqBody.getAccountHolder().getUpiId());
        inquiryInfo.setCredentials(credentialsInfo);

        BalanceInfo balanceInfo = npciService.balanceInquiry(inquiryInfo);

        CheckBalanceResDTO response = new CheckBalanceResDTO();
        CheckBalanceResponseBody responseBody = new CheckBalanceResponseBody();
        responseBody.setBalance(balanceInfo.getBalanceAmount());
        responseBody.setUpiID(reqBody.getAccountHolder().getUpiId());
        response.setBody(responseBody);



        return ResponseEntity.ok(response);
    }

}

