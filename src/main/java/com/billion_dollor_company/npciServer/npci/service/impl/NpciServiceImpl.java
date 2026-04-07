package com.billion_dollor_company.npciServer.npci.service.impl;

import com.billion_dollor_company.npciServer.common.exceptions.customExceptions.CryptographyException;
import com.billion_dollor_company.npciServer.domain.models.BalanceInfo;
import com.billion_dollor_company.npciServer.domain.models.BalanceInquiryInfo;
import com.billion_dollor_company.npciServer.payloads.fetchKeys.FetchKeysResDTO;
import com.billion_dollor_company.npciServer.payloads.registration.RegistrationReqDTO;
import com.billion_dollor_company.npciServer.payloads.registration.RegistrationResDTO;
import com.billion_dollor_company.npciServer.payloads.transaction.TransactionReqDTO;
import com.billion_dollor_company.npciServer.payloads.transaction.TransactionResDTO;
import com.billion_dollor_company.npciServer.bank.service.BankApiService;
import com.billion_dollor_company.npciServer.cryptography.service.CryptographyService;
import com.billion_dollor_company.npciServer.npci.service.NpciService;
import com.billion_dollor_company.npciServer.common.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NpciServiceImpl implements NpciService {

    private final BankApiService bankApiService;

    private final CryptographyService cryptographyService;

    @Autowired
    public NpciServiceImpl(BankApiService bankApiService, CryptographyService cryptographyService) {
        this.bankApiService = bankApiService;
        this.cryptographyService = cryptographyService;
    }

    @Override
    public BalanceInfo getAccountBalance(BalanceInquiryInfo requestInfo) {
        // Decrypt the password with NPCI private key.
        String encryptedPassword = requestInfo.getCredentials().getEncryptedPassword();

        // Decrypt and re encrypt the password with bank public key.
        encryptedPassword = cryptographyService.decryptAndReEncryptPW(encryptedPassword);

        // reuse the same object but now with changed encryptedPassword.
        requestInfo.getCredentials().setEncryptedPassword(encryptedPassword);

        // Forward the req to bank and send back the res received
        return bankApiService.getAccountBalance(requestInfo);
    }

    @Override
    public RegistrationResDTO registration(RegistrationReqDTO requestInfo) {
        String encryptedText = requestInfo.getEncryptedText();

        // this text was first decrypted with the aes key. and then some padding was added to it. the re-encrypted.
        String reEncryptedText = cryptographyService.decryptAndReEncryptChallenge(encryptedText);

        if (reEncryptedText == null)
            throw new CryptographyException(Constants.Values.ERROR_IN_AES);

        return new RegistrationResDTO(Constants.Status.SUCCESS, reEncryptedText);
    }

    @Override
    public FetchKeysResDTO fetchKeys() {
        FetchKeysResDTO response = FetchKeysResDTO.builder()
                .status(Constants.Status.SUCCESS)
                .message(Constants.Keys.NPCI_PUBLIC_KEY)
                .build();
        return response;
    }

    @Override
    public TransactionResDTO initiateTransaction(TransactionReqDTO requestInfo) {
        // Get the encrypted password from the request.
        String encryptedPassword = requestInfo.getEncryptedPassword();

        // Decrypt and re encrypt the password with bank public key.
        encryptedPassword = cryptographyService.decryptAndReEncryptPW(encryptedPassword);

        // if the password entered was wrong, then the service returns null. no point of sending null value forward. throw error.
        if (encryptedPassword == null)
            throw new CryptographyException(Constants.Values.ERROR_IN_CRYPTOGRAPHY);

        // reuse the same object. because why not.
        requestInfo.setEncryptedPassword(encryptedPassword);

        // Forward the req to bank and send back the res received
        return bankApiService.initiateTransaction(requestInfo);
    }


}


