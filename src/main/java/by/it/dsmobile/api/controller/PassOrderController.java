package by.it.dsmobile.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import by.it.dsmobile.api.dto.request.PassOrderRequest;
import by.it.dsmobile.core.service.PassOrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pass-orders")
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@AllArgsConstructor
@Validated
@Tag(name = "Заказ пропусков")
public class PassOrderController {

    private final PassOrderService passOrderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Запрос на выпуск нового пропуска")
    public void create(@Valid @NotNull @RequestBody final PassOrderRequest passOrderRequest) {
        passOrderService.create(passOrderRequest);
    }

}
