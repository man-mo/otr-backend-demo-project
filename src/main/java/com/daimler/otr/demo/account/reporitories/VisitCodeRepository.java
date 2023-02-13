package com.daimler.otr.demo.account.reporitories;

import com.daimler.otr.demo.account.model.entities.VisitCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitCodeRepository extends JpaRepository<VisitCode, Integer> {
    boolean existsByValue(String value);
}
