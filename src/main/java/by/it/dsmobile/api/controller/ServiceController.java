package by.it.dsmobile.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import by.it.dsmobile.api.dto.response.ServiceResponse;
import by.it.dsmobile.core.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/services")
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RequiredArgsConstructor
@Tag(name = "Услуги")
public class ServiceController {

    private final ServiceService serviceService;

    @GetMapping
    @Operation(summary = "Получение доступных услуг")
    public List<ServiceResponse> getAllServices() {
        return new ArrayList<>();
    }
}
