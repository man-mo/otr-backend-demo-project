package com.daimler.otr.demo.account.builder;

import com.daimler.otr.demo.account.model.entities.User;
import com.daimler.otr.demo.account.model.enums.RoleCode;
import com.daimler.otr.demo.account.reporitories.UserRepository;
import com.daimler.otr.demo.account.utils.SpringApplicationContext;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserBuilder {
    private User user = new User();

    public static UserBuilder withDefault() {
        return new UserBuilder().withId(1)
                                .withActive(true)
                                .withName("张三")
                                .withUsername("zhangsan")
                                .withPassword("12313")
                                .withPhoneNumber("15512777777")
                                .withRoleCode(RoleCode.CUSTOMER)
                                .withMoney(BigDecimal.valueOf(0));
    }

    public User build() {
        return user;
    }

    public User persist() {
        UserRepository repository = SpringApplicationContext.getBean(UserRepository.class);
        return repository.saveAndFlush(user);
    }

    public UserBuilder withActive(boolean isActive) {
        user.setActive(isActive);
        return this;
    }

    public UserBuilder withCreatedAt(Date createdAt) {
        user.setCreatedAt(createdAt);
        return this;
    }

    public UserBuilder withId(Integer id) {
        user.setId(id);
        return this;
    }

    public UserBuilder withMoney(BigDecimal money) {
        user.setMoney(money);
        return this;
    }

    public UserBuilder withName(String name) {
        user.setName(name);
        return this;
    }

    public UserBuilder withPassword(String password) {
        user.setPassword(password);
        return this;
    }

    public UserBuilder withPhoneNumber(String phoneNumber) {
        user.setPhoneNumber(phoneNumber);
        return this;
    }

    public UserBuilder withRoleCode(RoleCode roleCode) {
        user.setRoleCode(roleCode);
        return this;
    }

    public UserBuilder withUpdatedAt(Date updatedAt) {
        user.setUpdatedAt(updatedAt);
        return this;
    }

    public UserBuilder withUsername(String username) {
        user.setUsername(username);
        return this;
    }
}
