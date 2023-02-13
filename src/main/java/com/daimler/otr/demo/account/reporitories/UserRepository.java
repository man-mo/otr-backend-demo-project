package com.daimler.otr.demo.account.reporitories;

import com.daimler.otr.demo.account.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
