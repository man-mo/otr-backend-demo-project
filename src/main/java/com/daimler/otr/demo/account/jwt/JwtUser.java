package com.daimler.otr.demo.account.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtUser {
    private Integer id;

    private String username;

    private String roleCode;

    private String name;

    private List<String> privileges;

    private List<GroupDTO> groups;
}
