package com.daimler.otr.demo.account.utils.mapper;

import com.daimler.otr.demo.account.model.dto.CreateUserRequest;
import com.daimler.otr.demo.account.model.dto.UserDTO;
import com.daimler.otr.demo.account.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", source = "encodePassword")
    User mapToUser(CreateUserRequest createUserRequest, String encodePassword);

    UserDTO mapToUserDTO(User user);

}
