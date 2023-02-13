package com.daimler.otr.demo.account.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyRegisterAccountResponse {

    private String uri;

    private String acaId;
}
