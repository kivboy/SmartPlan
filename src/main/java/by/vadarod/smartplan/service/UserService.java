package by.vadarod.smartplan.service;

import by.vadarod.smartplan.dto.user.UserCreateRequest;
import by.vadarod.smartplan.dto.user.UserContactProjection;
import by.vadarod.smartplan.dto.user.UserResponse;
import by.vadarod.smartplan.dto.user.UserUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserResponse addUser(UserCreateRequest createRequest);
    UserResponse getUserById(Long userId);
    UserResponse deleteUserById(Long userId);
    UserResponse updateUser(Long userId, UserUpdateRequest updateRequest);
    Page<UserResponse> getUsers(int page, int size, String sortBy, String direction);
    UserDetails getUserInfo(String username);
    UserDetails addUserOAuth(UserCreateRequest createRequest);
    Page<UserContactProjection> getUsersContacts(int page, String firstNamePattern, String lastNamePattern);
}
