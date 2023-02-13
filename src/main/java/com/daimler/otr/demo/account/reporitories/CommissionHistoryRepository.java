package com.daimler.otr.demo.account.reporitories;

import com.daimler.otr.demo.account.model.entities.CommissionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommissionHistoryRepository extends JpaRepository<CommissionHistory, Integer> {
    boolean existsByOrderId(String orderId);
}
