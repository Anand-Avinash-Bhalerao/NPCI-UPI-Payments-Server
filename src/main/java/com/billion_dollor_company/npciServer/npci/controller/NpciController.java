package com.billion_dollor_company.npciServer.npci.controller;

import com.billion_dollor_company.npciServer.common.payload.request.BaseReqDTO;
import com.billion_dollor_company.npciServer.npci.mapper.NpciMapper;
import com.billion_dollor_company.npciServer.npci.payload.checkBalance.request.CheckBalanceReqBody;
import com.billion_dollor_company.npciServer.npci.payload.checkBalance.request.CheckBalanceReqDTO;
import com.billion_dollor_company.npciServer.npci.payload.checkBalance.response.CheckBalanceResDTO;
import com.billion_dollor_company.npciServer.payloads.fetchKeys.FetchKeysResDTO;
import com.billion_dollor_company.npciServer.payloads.registration.RegistrationReqDTO;
import com.billion_dollor_company.npciServer.payloads.registration.RegistrationResDTO;
import com.billion_dollor_company.npciServer.payloads.transaction.TransactionReqDTO;
import com.billion_dollor_company.npciServer.payloads.transaction.TransactionResDTO;
import com.billion_dollor_company.npciServer.npci.service.NpciService;
import com.billion_dollor_company.npciServer.common.constants.Constants;
import com.billion_dollor_company.npciServer.common.util.MessagePrinter;
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

//    @PostMapping("/checkBalance")
//    public ResponseEntity<BalanceResDTO> getAccountBalance(@RequestBody BalanceReqDTO request) {
//
//        BalanceInquiryInfo inquiryInfo = npciMapper.balanceReqDTOToBalanceInquiryInfo(request);
//
//        // getAccountBalance forwards the req to bank by decrypting and re-encrypting the password.
//        BalanceInfo balanceInfo = npciService.getAccountBalance(inquiryInfo);
//
//
//
//        // If the response status code was BAD_REQUEST then send Failed, 400 otherwise Success 200.
//        if (responseDTO.getStatus().equals(Constants.Status.FAILED)) {
//            return ResponseEntity.badRequest().body(responseDTO);
//        }
//        return ResponseEntity.ok().body(responseDTO);
//    }

    @PostMapping("/checkBalance")
    public ResponseEntity<CheckBalanceResDTO> getAccountBalance(@RequestBody BaseReqDTO<CheckBalanceReqBody> request) {


        return null;
    }


    @PostMapping("/transaction")
    public ResponseEntity<TransactionResDTO> initiateTransaction(@RequestBody TransactionReqDTO request) {

        MessagePrinter.printMessage(Constants.MessagePrinter.Server.NPCI, Constants.MessagePrinter.MethodType.InitiateTransaction, request);

        // initiateTransaction forwards the req to bank by decrypting and re-encrypting the password.
        TransactionResDTO responseDTO = npciService.initiateTransaction(request);

        // If the response status code was BAD_REQUEST then send Failed, 400 otherwise Success 200.
        if (responseDTO.getStatus().equals(Constants.Status.FAILED)) {
            return ResponseEntity.badRequest().body(responseDTO);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/registration")
    public ResponseEntity<RegistrationResDTO> register(@RequestBody RegistrationReqDTO request) {

        MessagePrinter.printMessage(Constants.MessagePrinter.Server.NPCI, Constants.MessagePrinter.MethodType.Register, request);

        RegistrationResDTO responseDTO = npciService.registration(request);

        // If the response status code was BAD_REQUEST then send Failed, 400 otherwise Success 200.
        if (responseDTO.getStatus().equals(Constants.Status.FAILED)) {
            return ResponseEntity.badRequest().body(responseDTO);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/fetchKeys")
    public ResponseEntity<FetchKeysResDTO> fetchKeys() {

        MessagePrinter.printMessage(Constants.MessagePrinter.Server.NPCI, Constants.MessagePrinter.MethodType.FetchKeys, new Object());

        FetchKeysResDTO responseDTO = npciService.fetchKeys();

        // If the response status code was BAD_REQUEST then send Failed, 400 otherwise Success 200.
        if (responseDTO.getStatus().equals(Constants.Status.FAILED)) {
            return ResponseEntity.badRequest().body(responseDTO);
        }
        return ResponseEntity.ok().body(responseDTO);
    }


}

