package by.vadarod.smartplan.services;

import by.vadarod.smartplan.entity.User;

public interface UserService {
    User addUser(User user);
    User getUserById(Long userId);
    void deleteUserById(Long userId);
}
