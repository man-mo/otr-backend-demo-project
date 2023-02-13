package com.daimler.otr.demo.account.controllers;

import com.daimler.otr.demo.account.IntegrationTestBase;
import com.daimler.otr.demo.account.builder.CreateUserDTOBuilder;
import com.daimler.otr.demo.account.builder.JwtUserBuilder;
import com.daimler.otr.demo.account.builder.VisitCodeBuilder;
import com.daimler.otr.demo.account.jwt.JwtUser;
import com.daimler.otr.demo.account.model.dto.CreateUserRequest;
import com.daimler.otr.demo.account.model.entities.User;
import com.daimler.otr.demo.account.model.enums.ErrorCode;
import com.daimler.otr.demo.account.model.enums.RoleCode;
import com.daimler.otr.demo.account.reporitories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

class UserControllerTest extends IntegrationTestBase {

    public static final String REQUEST_API = "api/user";
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void should_create_user_successful() {
        VisitCodeBuilder.withDefault()
                        .withValue("visitCode")
                        .persist();
        CreateUserRequest userDTO = CreateUserDTOBuilder.withDefault()
                                                        .name("张三")
                                                        .roleCode(RoleCode.CUSTOMER)
                                                        .phoneNumber("15512778999")
                                                        .visitCode("visitCode")
                                                        .username("zhangsan121")
                                                        .password("abcdUIO123")
                                                        .build();
        given().body(userDTO)
               .post(REQUEST_API)
               .then()
               .statusCode(HttpStatus.CREATED.value())
               .body("id", is(not(nullValue())))
               .body("name", is(userDTO.getName()))
               .body("phoneNumber", is(userDTO.getPhoneNumber()))
               .body("money", is(0))
               .body("roleCode", is(userDTO.getRoleCode().name()))
               .body("username", is(userDTO.getUsername()));

        User user = userRepository.findAll().get(0);
        assertEquals(userDTO.getName(), user.getName());
        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getPhoneNumber(), user.getPhoneNumber());
        assertSame(RoleCode.CUSTOMER, user.getRoleCode());

        assertTrue(passwordEncoder.matches(userDTO.getPassword(), user.getPassword()));
        assertTrue(user.isActive());
    }

    @Test
    void should_throw_exception_when_phone_number_length_is_less_than_11() {
        CreateUserRequest request = CreateUserDTOBuilder.withDefault()
                                                        .phoneNumber("123456789")
                                                        .build();

        given().body(request)
               .post(REQUEST_API)
               .then()
               .statusCode(HttpStatus.UNAUTHORIZED.value())
               .body("message", is("手机号码输入有误！"))
               .body("error_code", is(ErrorCode.INVALID_USER_INFO.value()));
    }

    @Test
    void should_throw_exception_when_visit_code_not_existed() {
        VisitCodeBuilder.withDefault()
                        .withValue("osiueowiure")
                        .persist();
        CreateUserRequest request = CreateUserDTOBuilder.withDefault()
                                                        .visitCode("xxxxxxx")
                                                        .build();

        given().body(request)
               .post(REQUEST_API)
               .then()
               .statusCode(HttpStatus.UNAUTHORIZED.value())
               .body("message", is("邀请码不存在，请检查！"))
               .body("error_code", is(ErrorCode.VISIT_CODE_NOT_FOUND.value()));
    }

    @Test
    void should_return_jwt_user_given_token_header() {
        JwtUser jwtUser = JwtUserBuilder.withDefault()
                                        .build();
        given(jwtUser).get(REQUEST_API + "/jwtuser")
                      .then()
                      .statusCode(HttpStatus.OK.value())
                      .body("id", is(jwtUser.getId()))
                      .body("name", is(jwtUser.getName()))
                      .body("roleCode", is(jwtUser.getRoleCode()))
                      .body("username", is(jwtUser.getUsername()));
    }
}