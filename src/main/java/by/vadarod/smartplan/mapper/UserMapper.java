package by.vadarod.smartplan.mapper;

import by.vadarod.smartplan.dto.user.UserCreateRequest;
import by.vadarod.smartplan.dto.user.UserOauthCreateRequest;
import by.vadarod.smartplan.dto.user.UserResponse;
import by.vadarod.smartplan.dto.user.UserUpdateRequest;
import by.vadarod.smartplan.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserCreateRequest createRequest);
    @Mapping(target = "role", constant = "USER")
    @Mapping(target = "enabled", constant = "true")
    User toEntity(UserOauthCreateRequest createRequest);
    UserResponse toResponse(User user);
    @Mapping(target = "password", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest updateRequest);
}
