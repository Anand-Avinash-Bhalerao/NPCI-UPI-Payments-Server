package com.billion_dollor_company.npciServer.npci.payload.checkBalance.response;

import com.billion_dollor_company.npciServer.common.payload.response.ResponseBody;
import lombok.Data;

@Data
public class CheckBalanceResponseBody implements ResponseBody {
    private String upiID;
    private double balance;
}
