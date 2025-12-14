package by.vadarod.smartplan.services;

import by.vadarod.smartplan.dto.user.UserCreateRequest;
import by.vadarod.smartplan.dto.user.UserResponse;
import by.vadarod.smartplan.dto.user.UserUpdateRequest;
import by.vadarod.smartplan.entity.User;
import by.vadarod.smartplan.exception.DuplicateEntityException;
import by.vadarod.smartplan.exception.EntityNotFoundException;
import by.vadarod.smartplan.logging.LoggingAnnotation;
import by.vadarod.smartplan.mapper.UserMapper;
import by.vadarod.smartplan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        if (!userRepository.existsByLogin(createRequest.getLogin())) {
            User user = userMapper.toEntity(createRequest);
            return userMapper.toResponse(userRepository.save(user));
        } else {
            throw new DuplicateEntityException("Login уже используется");
        }
    }

    @Override
    @LoggingAnnotation
    public UserResponse getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return userMapper.toResponse(user.get());
        } else {
            throw new EntityNotFoundException("Не найден User по id=" + userId);
        }
    }

    @Override
    public UserResponse deleteUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.deleteById(userId);
            return userMapper.toResponse(userOptional.get());
        } else {
            throw new EntityNotFoundException("Не найден User по id=" + userId);
        }
    }

    @Override
    public UserResponse updateUser(Long userId, UserUpdateRequest updateRequest) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userMapper.updateUser(user, updateRequest);
            return userMapper.toResponse(userRepository.save(user));
        } else {
            throw new EntityNotFoundException("Не найден User по id=" + userId);
        }
    }

    @Override
    public Page<UserResponse> getUsers(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Page<User> usersPage = userRepository.findAll(PageRequest.of(page, size, sort));

        // преобразуем Page<User> → Page<UserResponse> через MapStruct
        return usersPage.map(userMapper::toResponse);
    }
}
