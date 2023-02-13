package com.daimler.otr.demo.account.listener;

import com.daimler.otr.demo.account.IntegrationTestBase;
import com.daimler.otr.demo.account.builder.CommissionHistoryBuilder;
import com.daimler.otr.demo.account.model.entities.Order;
import com.daimler.otr.demo.account.model.entities.User;
import com.daimler.otr.demo.account.model.enums.CommissionStatus;
import com.daimler.otr.demo.account.reporitories.UserRepository;
import com.google.gson.Gson;
import com.daimler.otr.demo.account.builder.OrderBuilder;
import com.daimler.otr.demo.account.builder.UserBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static com.daimler.otr.demo.account.utils.BigDecimalUtils.assertBigDecimal;

public class OrderListenerIntegrationTest extends IntegrationTestBase {

    @Autowired
    private OrderListener orderListener;

    @Autowired
    private UserRepository userRepository;

    @Test
    void should_pay_commission_10_yuan_when_receive_order_with_price_is_1000_yuan() {
        User user = UserBuilder.withDefault()
                               .withId(1)
                               .withMoney(BigDecimal.valueOf(0))
                               .persist();

        Order originOrder = OrderBuilder.withDefault()
                                        .id("O12345")
                                        .price(BigDecimal.valueOf(1000))
                                        .storeCustomerUserId(user.getId())
                                        .build();
        Gson gson = new Gson();
        String jsonOrder = gson.toJson(originOrder);
        OrderMessage orderMessage = new OrderMessage("messageId", jsonOrder);

        orderListener.onMessage(orderMessage);

        assertBigDecimal(BigDecimal.valueOf(10), userRepository.findById(user.getId()).get().getMoney());
    }

    @Test
    void should_do_not_pay_commission_when_receive_order_which_has_payed() {
        String orderId = "O12345";
        User user = UserBuilder.withDefault()
                               .withId(1)
                               .withMoney(BigDecimal.valueOf(0))
                               .persist();

        Order originOrder = OrderBuilder.withDefault()
                                        .id(orderId)
                                        .price(BigDecimal.valueOf(1000))
                                        .storeCustomerUserId(user.getId())
                                        .build();
        CommissionHistoryBuilder.withDefault()
                                .withOrderId(orderId)
                                .withPrice(BigDecimal.valueOf(1000))
                                .withCommissionStatus(CommissionStatus.PAYED)
                                .persist();

        Gson gson = new Gson();
        String jsonOrder = gson.toJson(originOrder);
        OrderMessage orderMessage = new OrderMessage("messageId", jsonOrder);

        orderListener.onMessage(orderMessage);

        assertBigDecimal(BigDecimal.valueOf(0), userRepository.findById(user.getId()).get().getMoney());
    }
}
