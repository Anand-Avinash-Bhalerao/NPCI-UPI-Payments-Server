package com.billion_dollor_company.npciServer.npci.payload.checkBalance.request;

import com.billion_dollor_company.npciServer.common.payload.request.RequestBody;
import com.billion_dollor_company.npciServer.npci.payload.AccountHolder;
import com.billion_dollor_company.npciServer.npci.payload.Credentials;
import lombok.Data;

@Data
public class CheckBalanceReqBody implements RequestBody {
    private AccountHolder accountHolder;
    private Credentials credentials;
}
