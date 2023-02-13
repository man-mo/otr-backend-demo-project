package com.daimler.otr.demo.account.exceptions;

import com.daimler.otr.demo.account.model.enums.ErrorCode;

public abstract class BaseUnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = 3130677615686138367L;

    BaseUnauthorizedException(String message) {
        super(message);
    }

    public abstract ErrorCode getErrorCode();
}
