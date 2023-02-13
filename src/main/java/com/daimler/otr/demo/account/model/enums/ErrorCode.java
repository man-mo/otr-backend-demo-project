package com.daimler.otr.demo.account.model.enums;

public enum ErrorCode {
    INVALID_USER_INFO("invalid_user_info"),
    VISIT_CODE_NOT_FOUND("visit_code_not_found");

    private String value;

    ErrorCode(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
