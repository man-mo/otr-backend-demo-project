package com.daimler.otr.demo.account.controllers;

import com.daimler.otr.demo.account.jwt.JwtUser;
import com.daimler.otr.demo.account.model.dto.CreateUserRequest;
import com.daimler.otr.demo.account.model.dto.UserDTO;
import com.daimler.otr.demo.account.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @GetMapping("/jwtuser")
    public JwtUser demoJwtUser(JwtUser jwtUser) {
        return jwtUser;
    }
}
