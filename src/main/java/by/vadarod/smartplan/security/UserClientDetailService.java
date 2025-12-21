package by.vadarod.smartplan.security;

import by.vadarod.smartplan.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserClientDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userService.getUserInfo(username);
        if (userDetails == null) {
            throw new RuntimeException("Пользователь " + username + " не найден!");
        }
        return userDetails;
    }
}
