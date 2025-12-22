package by.vadarod.smartplan.controller;

import by.vadarod.smartplan.dto.user.UserCreateRequest;
import by.vadarod.smartplan.dto.user.UserPageResponse;
import by.vadarod.smartplan.dto.user.UserResponse;
import by.vadarod.smartplan.dto.user.UserUpdateRequest;
import by.vadarod.smartplan.exception.model.ErrorResponse;
import by.vadarod.smartplan.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
@Tag(name = "Пользователи", description = "Сервисы по работе с пользователями")
public class UserRestController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Добавление пользователя", description = "Добавление нового пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Duplicate user name",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)))
    })
    public UserResponse addUser(@RequestBody @Validated UserCreateRequest createRequest) {
        return userService.addUser(createRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение пользователя", description = "Получение пользователя по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long userId) {
        UserResponse userResponse = userService.getUserById(userId);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновление данных пользователя", description = "Редактирование пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public UserResponse updateUser(@PathVariable("id") Long userId, @RequestBody @Validated UserUpdateRequest updateRequest) {
        return userService.updateUser(userId, updateRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пользователя", description = "Удаление пользователя по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class)))
    })
    public UserResponse delete(@PathVariable("id") Long id) {
        return userService.deleteUserById(id);
    }

    @GetMapping("/list")
    @Operation(summary = "Список пользователей", description = "Получение списка пользователей постранично с сортировкой по выбранному полю")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = UserPageResponse.class)))
    })
    public Page<UserResponse> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return userService.getUsers(page, size, sortBy, direction);
    }

}
