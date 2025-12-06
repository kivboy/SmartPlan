package by.vadarod.smartplan.controller;

import by.vadarod.smartplan.dto.user.UserCreateRequest;
import by.vadarod.smartplan.dto.user.UserResponse;
import by.vadarod.smartplan.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
@Tag(name = "Пользователи", description = "Сервисы по работе с пользователями")
public class UserRestController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Добавление пользователя", description = "Добавление нового пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    public UserResponse addUser(@RequestBody @Validated UserCreateRequest createRequest) {
        return userService.addUser(createRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение пользователя", description = "Получение пользователя по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long userId) {
        UserResponse userResponse = userService.getUserById(userId);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пользователя", description = "Удаление пользователя по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public void delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }
}
