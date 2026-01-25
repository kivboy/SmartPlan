package by.vadarod.smartplan.jwt;

import by.vadarod.smartplan.dto.user.UserCreateRequest;
import by.vadarod.smartplan.dto.user.UserOauthCreateRequest;
import by.vadarod.smartplan.jwt.model.JwtAuthenticationRequest;
import by.vadarod.smartplan.jwt.model.JwtAuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "oauth")
@Tag(name = "OAuth", description = "Сервисы аутентификации")
public class JwtController {

    private final SignService signService;

    @Operation(summary = "Логин пользователя", description = "Логин пользователя по имени и паролю")
    @RequestMapping(value = "sign-in", method = RequestMethod.POST)
    public JwtAuthenticationResponse signIn(@RequestBody @Validated JwtAuthenticationRequest authenticationRequest) {
        return signService.signIn(authenticationRequest);
    }

    @Operation(summary = "Регистрация пользователя", description = "Регистрация нового пользователя")
    @RequestMapping(value = "sign-up", method = RequestMethod.POST)
    public JwtAuthenticationResponse signUp(@RequestBody @Validated UserOauthCreateRequest userCreateRequest) {
        return signService.signUp(userCreateRequest);
    }
}
