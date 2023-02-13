package com.daimler.otr.demo.account.model.dto;

import com.daimler.otr.demo.account.model.enums.RoleCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;

    private String username;

    private String name;

    private String phoneNumber;

    private BigDecimal money;

    private RoleCode roleCode;

    private boolean isActive;
}
