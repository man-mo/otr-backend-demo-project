package com.daimler.otr.demo.account.builder;

import com.daimler.otr.demo.account.model.entities.CommissionHistory;
import com.daimler.otr.demo.account.model.enums.CommissionStatus;
import com.daimler.otr.demo.account.utils.SpringApplicationContext;
import com.daimler.otr.demo.account.reporitories.CommissionHistoryRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommissionHistoryBuilder {
    private CommissionHistory commissionHistory = new CommissionHistory();

    public static CommissionHistoryBuilder withDefault() {
        return new CommissionHistoryBuilder();
    }

    public CommissionHistory build() {
        return commissionHistory;
    }

    public CommissionHistory persist() {
        CommissionHistoryRepository repository = SpringApplicationContext.getBean(CommissionHistoryRepository.class);
        return repository.saveAndFlush(commissionHistory);
    }

    public CommissionHistoryBuilder withCommissionStatus(CommissionStatus commissionStatus) {
        commissionHistory.setCommissionStatus(commissionStatus);
        return this;
    }

    public CommissionHistoryBuilder withCreatedAt(Date createdAt) {
        commissionHistory.setCreatedAt(createdAt);
        return this;
    }

    public CommissionHistoryBuilder withId(Integer id) {
        commissionHistory.setId(id);
        return this;
    }

    public CommissionHistoryBuilder withOrderId(String orderId) {
        commissionHistory.setOrderId(orderId);
        return this;
    }

    public CommissionHistoryBuilder withPrice(BigDecimal price) {
        commissionHistory.setPrice(price);
        return this;
    }

    public CommissionHistoryBuilder withUpdatedAt(Date updatedAt) {
        commissionHistory.setUpdatedAt(updatedAt);
        return this;
    }
}
