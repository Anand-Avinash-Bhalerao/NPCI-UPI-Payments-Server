package com.billion_dollor_company.npciServer.payloads;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusResDTO {
    public String status;
    public String message;
}
