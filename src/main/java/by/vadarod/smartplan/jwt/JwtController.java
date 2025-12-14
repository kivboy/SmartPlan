package by.vadarod.smartplan.jwt;

import by.vadarod.smartplan.dto.user.UserCreateRequest;
import by.vadarod.smartplan.jwt.model.JwtAuthenticationRequest;
import by.vadarod.smartplan.jwt.model.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "oauth")
public class JwtController {

    private final SignService signService;

    @RequestMapping(value = "sign-in", method = RequestMethod.POST)
    public JwtAuthenticationResponse signIn(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        return signService.signIn(authenticationRequest);
    }

    @RequestMapping(value = "sign-up", method = RequestMethod.POST)
    public JwtAuthenticationResponse signUp(@RequestBody UserCreateRequest userCreateRequest) {
        return signService.signUp(userCreateRequest);
    }
}
