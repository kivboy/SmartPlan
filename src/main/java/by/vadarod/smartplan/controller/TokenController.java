package by.vadarod.smartplan.controller;

import by.vadarod.smartplan.jwt.model.JwtAuthenticationResponse;
import by.vadarod.smartplan.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("oauth")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/refresh")
    public JwtAuthenticationResponse refresh(@RequestParam("refresh_token")
                                             String token) {
        return tokenService.refresh(token);
    }
}
