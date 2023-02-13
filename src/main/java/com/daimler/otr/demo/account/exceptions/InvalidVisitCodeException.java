package com.daimler.otr.demo.account.exceptions;

import com.daimler.otr.demo.account.model.enums.ErrorCode;

public class InvalidVisitCodeException extends BaseUnauthorizedException {
    private static final long serialVersionUID = 4449660254133844486L;

    public InvalidVisitCodeException() {
        super("邀请码不存在，请检查！");
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.VISIT_CODE_NOT_FOUND;
    }
}
