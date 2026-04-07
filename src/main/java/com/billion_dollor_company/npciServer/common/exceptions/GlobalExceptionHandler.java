package com.billion_dollor_company.npciServer.common.exceptions;

import com.billion_dollor_company.npciServer.common.exceptions.customExceptions.CryptographyException;
import com.billion_dollor_company.npciServer.payloads.StatusResDTO;
import com.billion_dollor_company.npciServer.common.constants.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CryptographyException.class)
    public ResponseEntity<StatusResDTO> cryptographyException(CryptographyException exception) {
        String message = exception.getMessage();
        StatusResDTO responseInfo = new StatusResDTO(Constants.Status.FAILED, message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_XML)
                .body(responseInfo);
    }

}
