package by.it.dsmobile.core.service;

import by.it.dsmobile.core.exception.ValueNotFoundException;
import by.it.dsmobile.core.repository.ServiceRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public by.it.dsmobile.core.model.Service findById(Integer id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ValueNotFoundException("No service found [id = %s] ".formatted(id)));
    }
}
