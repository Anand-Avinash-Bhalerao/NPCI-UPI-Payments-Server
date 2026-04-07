package com.billion_dollor_company.npciServer.npci.mapper;

import com.billion_dollor_company.npciServer.domain.models.BalanceInquiryInfo;
import com.billion_dollor_company.npciServer.npci.payloads.checkBalance.BalanceReqDTO;
import com.billion_dollor_company.npciServer.npci.payloads.checkBalance.BalanceResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NpciMapper {

    @Mapping(source = "upiID", target = "credentials.upiId")
    @Mapping(source = "encryptedPassword", target = "credentials.encryptedPassword")
    BalanceInquiryInfo balanceReqDTOToBalanceInquiryInfo(BalanceReqDTO balanceReqDTO);

}
