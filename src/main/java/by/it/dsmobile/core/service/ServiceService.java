package by.it.dsmobile.core.service;

import by.it.dsmobile.api.dto.response.ServiceResponse;
import by.it.dsmobile.api.mapper.ServiceMapper;
import by.it.dsmobile.core.exception.ValueNotFoundException;
import by.it.dsmobile.core.repository.ServiceRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.List;

import static by.it.dsmobile.core.repository.specification.ServiceSpecification.hasDisposable;

@Service
@AllArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceResponse findById(Integer id) {
        return serviceRepository
                .findById(id)
                .map(serviceMapper::toServiceResponse)
                .orElseThrow(() -> new ValueNotFoundException("No service found [id = %s] ".formatted(id)));
    }

    public List<ServiceResponse> find(final Boolean disposable) {
        return serviceRepository
                .findAll(hasDisposable(disposable))
                .stream().map(serviceMapper::toServiceResponse)
                .toList();
    }

}
