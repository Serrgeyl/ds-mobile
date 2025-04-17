package by.it.dsmobile.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import by.it.dsmobile.api.dto.request.ServiceOrder;
import by.it.dsmobile.core.service.ServiceToUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/service-orders")
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@AllArgsConstructor
@Validated
@Tag(name = "Подключение услуг")
public class ServiceOrderController {

    private final ServiceToUserService serviceToUserService;

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Запрос на подключение услуги информирования")
    public void updateServiceOrder(@Valid @NotNull @RequestBody final ServiceOrder serviceOrder) {
        serviceToUserService.update(serviceOrder);
    }


}
