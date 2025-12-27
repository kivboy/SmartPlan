package by.vadarod.smartplan.security;

import by.vadarod.smartplan.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {
    public static UserDetails getCurrentUserOrThrow() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            throw new IllegalStateException("Пользователь не аутентифицирован");
        }
        return (UserDetails) authentication.getPrincipal();
    }

    public static Long getCurrentUserIdOrThrow() {
        return ((User)getCurrentUserOrThrow()).getId();
    }
}
