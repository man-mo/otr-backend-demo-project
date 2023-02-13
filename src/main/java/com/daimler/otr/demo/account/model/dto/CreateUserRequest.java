package com.daimler.otr.demo.account.model.dto;

import com.daimler.otr.demo.account.model.enums.RoleCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String username;

    private String password;

    private RoleCode roleCode;

    private String phoneNumber;

    private String name;

    private String visitCode;
}
