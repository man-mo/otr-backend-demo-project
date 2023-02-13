package com.daimler.otr.demo.account.jwt;

import java.util.Arrays;

public class JwtServiceUser extends JwtUser {
    public JwtServiceUser() {
        super();
        this.setId(0);
        this.setUsername("SERVICE_USER");
        this.setRoleCode("SERVICE_ROLE");
        this.setPrivileges(Arrays.asList("ROLE_SERVICE"));
    }
}
