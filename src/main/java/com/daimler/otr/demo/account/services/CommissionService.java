package com.daimler.otr.demo.account.services;

import com.daimler.otr.demo.account.model.entities.Order;
import com.daimler.otr.demo.account.model.entities.User;
import com.daimler.otr.demo.account.reporitories.CommissionHistoryRepository;
import com.daimler.otr.demo.account.reporitories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommissionService {

    private static final BigDecimal RATE = BigDecimal.valueOf(0.01);
    private final UserRepository userRepository;
    private final CommissionHistoryRepository commissionHistoryRepository;

    public void payCommission(Order order) {
        Optional<User> userOptional = userRepository.findById(order.getStoreCustomerUserId());
        if (!userOptional.isPresent()) {
            return;
        }
        if (commissionHistoryRepository.existsByOrderId(order.getId())) {
            log.error("find has payed order. orderId = {}", order.getId());
            return;
        }

        User user = userOptional.get();
        user.setMoney(calculateCommission(order, user));
        userRepository.save(user);
    }

    private BigDecimal calculateCommission(Order order, User user) {
        BigDecimal commissionAmount = order.getPrice().multiply(RATE);
        return user.getMoney().add(commissionAmount);
    }
}
