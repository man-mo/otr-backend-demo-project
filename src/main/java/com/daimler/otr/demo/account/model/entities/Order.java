package com.daimler.otr.demo.account.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private Integer storeCustomerUserId;
    private BigDecimal price;
}
