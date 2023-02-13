package com.daimler.otr.demo.account.utils;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BigDecimalUtils {

    public static void assertBigDecimal(BigDecimal value1, BigDecimal value2) {
        assertEquals(0, value1.compareTo(value2));
    }
}
