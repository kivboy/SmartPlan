package by.vadarod.smartplan.jwt;

import by.vadarod.smartplan.dto.user.UserCreateRequest;
import by.vadarod.smartplan.jwt.model.JwtAuthenticationRequest;
import by.vadarod.smartplan.jwt.model.JwtAuthenticationResponse;
import by.vadarod.smartplan.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationResponse signUp(UserCreateRequest createRequest) {
        UserDetails userDetails = userService.addUserOAuth(createRequest);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setAccessToken(jwtService.generateToken(userDetails));
        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse signIn(JwtAuthenticationRequest authenticationRequest) {
        var user = userDetailsService.loadUserByUsername(authenticationRequest.getLogin());
        var authToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getLogin(),
                authenticationRequest.getPassword(),
                user.getAuthorities()
        );
        authenticationManager.authenticate(authToken);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
        String jwt = jwtService.generateToken(user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setAccessToken(jwt);

        return jwtAuthenticationResponse;
    }
}
