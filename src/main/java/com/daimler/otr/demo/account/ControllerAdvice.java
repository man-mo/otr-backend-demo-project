package com.daimler.otr.demo.account;

import com.daimler.otr.demo.account.model.dto.ErrorResult;
import com.daimler.otr.demo.account.exceptions.BaseUnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(BaseUnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResult handleUnauthorizedException(BaseUnauthorizedException e) {
        return ErrorResult.builder()
                          .errorCode(e.getErrorCode().value())
                          .message(e.getMessage())
                          .build();
    }
}
