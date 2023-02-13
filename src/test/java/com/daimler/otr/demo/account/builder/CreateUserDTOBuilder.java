package com.daimler.otr.demo.account.builder;

import com.daimler.otr.demo.account.model.dto.CreateUserRequest;
import com.daimler.otr.demo.account.model.enums.RoleCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserDTOBuilder {

    public static CreateUserRequest.CreateUserRequestBuilder withDefault() {
        return CreateUserRequest.builder()
                                .name("张三")
                                .username("zhangsan121")
                                .roleCode(RoleCode.CUSTOMER)
                                .phoneNumber("15512778999")
                                .visitCode("asliuowiersldfkj")
                                .password("qawsedrf");
    }

}
