package com.daimler.otr.demo.account.builder;

import com.daimler.otr.demo.account.jwt.JwtUser;
import com.daimler.otr.demo.account.model.enums.RoleCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUserBuilder {

    public static JwtUser.JwtUserBuilder withDefault() {
        return JwtUser.builder()
                      .id(1)
                      .name("小明")
                      .roleCode(RoleCode.CUSTOMER.name())
                      .username("D8XIAOMING");
    }
}
