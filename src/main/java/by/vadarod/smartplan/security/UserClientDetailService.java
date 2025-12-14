package by.vadarod.smartplan.security;

import by.vadarod.smartplan.entity.User;
import by.vadarod.smartplan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserClientDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional= userRepository.findByLogin(username);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Пользователь " + username + " не найден!");
        }
        return userOptional.get();
    }
}
