package com.daimler.otr.demo.account.services;

import com.daimler.otr.demo.account.exceptions.InvalidUserInfoException;
import com.daimler.otr.demo.account.exceptions.InvalidVisitCodeException;
import com.daimler.otr.demo.account.model.dto.CreateUserRequest;
import com.daimler.otr.demo.account.model.dto.UserDTO;
import com.daimler.otr.demo.account.model.entities.User;
import com.daimler.otr.demo.account.reporitories.UserRepository;
import com.daimler.otr.demo.account.reporitories.VisitCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static com.daimler.otr.demo.account.utils.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private static final int PHONE_NUMBER_LENGTH = 11;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final VisitCodeRepository visitCodeRepository;

    @Transactional
    public UserDTO createUser(CreateUserRequest request) {
        if (request.getPhoneNumber().length() != PHONE_NUMBER_LENGTH) {
            throw new InvalidUserInfoException("手机号码输入有误！");
        }
        if (!visitCodeRepository.existsByValue(request.getVisitCode())) {
            throw new InvalidVisitCodeException();
        }
        User user = USER_MAPPER.mapToUser(request, passwordEncoder.encode(request.getPassword()));
        user.setMoney(BigDecimal.valueOf(0));
        user = userRepository.save(user);
        return USER_MAPPER.mapToUserDTO(user);
    }
}
