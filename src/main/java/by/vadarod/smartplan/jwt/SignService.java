package by.vadarod.smartplan.jwt;

import by.vadarod.smartplan.dto.user.UserCreateRequest;
import by.vadarod.smartplan.jwt.model.JwtAuthenticationRequest;
import by.vadarod.smartplan.jwt.model.JwtAuthenticationResponse;
import by.vadarod.smartplan.service.TokenService;
import by.vadarod.smartplan.service.UserService;
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
    private final TokenService tokenService;

    public JwtAuthenticationResponse signUp(UserCreateRequest createRequest) {
        UserDetails userDetails = userService.addUserOAuth(createRequest);
        String refreshToken = tokenService.generateRefreshToken(userDetails.getUsername());
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setAccessToken(jwtService.generateToken(userDetails));
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse signIn(JwtAuthenticationRequest authenticationRequest) {
        var userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getLogin());
        var authToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getLogin(),
                authenticationRequest.getPassword(),
                userDetails.getAuthorities()
        );
        authenticationManager.authenticate(authToken);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
        String jwt = jwtService.generateToken(userDetails);
        String refreshToken = tokenService.generateRefreshToken(userDetails.getUsername());
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        jwtAuthenticationResponse.setAccessToken(jwt);

        return jwtAuthenticationResponse;
    }
}
