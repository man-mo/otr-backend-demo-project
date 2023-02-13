package com.daimler.otr.demo.account.exceptions;

import com.daimler.otr.demo.account.model.enums.ErrorCode;

public class InvalidUserInfoException extends BaseUnauthorizedException {
    private static final long serialVersionUID = 4449660254133844486L;

    public InvalidUserInfoException(String message) {
        super(message);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.INVALID_USER_INFO;
    }
}
