package com.daimler.otr.demo.account.builder;

import com.daimler.otr.demo.account.model.entities.Order;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderBuilder {

    public static Order.OrderBuilder withDefault() {
        return Order.builder()
                    .id("O20202344")
                    .price(BigDecimal.valueOf(1000));
    }
}
