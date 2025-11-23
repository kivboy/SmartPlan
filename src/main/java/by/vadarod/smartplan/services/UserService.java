package by.vadarod.smartplan.services;

import by.vadarod.smartplan.dto.user.UserCreateRequest;
import by.vadarod.smartplan.dto.user.UserResponse;
import by.vadarod.smartplan.dto.user.UserUpdateRequest;

public interface UserService {
    UserResponse addUser(UserCreateRequest createRequest);
    UserResponse getUserById(Long userId);
    void deleteUserById(Long userId);
    void updateUser(UserUpdateRequest updateRequest);
}
