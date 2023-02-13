package com.daimler.otr.demo.account.listener;

import com.daimler.otr.demo.account.model.entities.Order;
import com.daimler.otr.demo.account.services.CommissionService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderListener {

    private final CommissionService commissionService;

    public void onMessage(OrderMessage orderMessage) {
        try {
            handleMessage(orderMessage);
        } catch (Exception e) {
            log.error("Handle Order message error: messageId={}, errorMessage={}", orderMessage.getMessageId(), e.getMessage());
        }
    }

    private void handleMessage(OrderMessage message) {
        String messageId = message.getMessageId();
        log.info("Handle Order message: messageId = {}", messageId);

        Gson gson = new Gson();
        Order order = gson.fromJson(message.getMessageContent(), Order.class);
        commissionService.payCommission(order);
        log.info("Order message handle finish");
    }
}
