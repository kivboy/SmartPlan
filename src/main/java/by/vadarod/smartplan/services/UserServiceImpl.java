package by.vadarod.smartplan.services;

import by.vadarod.smartplan.dto.user.UserCreateRequest;
import by.vadarod.smartplan.dto.user.UserResponse;
import by.vadarod.smartplan.dto.user.UserUpdateRequest;
import by.vadarod.smartplan.entity.User;
import by.vadarod.smartplan.mapper.UserMapper;
import by.vadarod.smartplan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse addUser(UserCreateRequest createRequest) {
        User user = userMapper.toEntity(createRequest);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return userMapper.toResponse(user.get());
        } else {
            return new UserResponse();
        }
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void updateUser(UserUpdateRequest updateRequest) {
        Optional<User> userOptional = userRepository.findById(updateRequest.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userMapper.updateUser(user, updateRequest);
            userRepository.save(user);
        } else {
            // throw exception
        }
    }
}
