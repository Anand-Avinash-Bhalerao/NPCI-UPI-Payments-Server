package com.billion_dollor_company.npciServer.common.payload.response;

import lombok.Data;

@Data
public class BaseResDTO <T extends ResponseBody>{
    private String status;
    private String message;
    private String code;
    T body;
    private Meta meta;
}
