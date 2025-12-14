package by.vadarod.smartplan.services;

import by.vadarod.smartplan.dto.user.UserCreateRequest;
import by.vadarod.smartplan.dto.user.UserResponse;
import by.vadarod.smartplan.dto.user.UserUpdateRequest;
import org.springframework.data.domain.Page;

public interface UserService {
    UserResponse addUser(UserCreateRequest createRequest);
    UserResponse getUserById(Long userId);
    UserResponse deleteUserById(Long userId);
    UserResponse updateUser(Long userId, UserUpdateRequest updateRequest);
    Page<UserResponse> getUsers(int page, int size, String sortBy, String direction);
}
