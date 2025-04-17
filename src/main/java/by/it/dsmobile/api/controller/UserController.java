package by.it.dsmobile.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import by.it.dsmobile.api.dto.response.RelatedUser;
import by.it.dsmobile.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RequiredArgsConstructor
@Tag(name = "Пользователи")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{id}/related-users")
    @Operation(summary = "Получение связанных пользователей (детей)")
    public List<RelatedUser> getRelatedUsers(@PathVariable final Integer id) {
        return userService.getRelatedUsers(id);
    }

    @GetMapping(value = "/{id}/balance")
    @Operation(summary = "Баланс пользователя")
    public Double getBalanceById(@PathVariable("id") final Integer id) {
        return userService.getBalanceById(id);
    }

}
