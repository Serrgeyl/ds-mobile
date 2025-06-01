package by.it.dsmobile.core.service;

import by.it.dsmobile.core.exception.ValueNotFoundException;
import by.it.dsmobile.core.model.Organization;
import by.it.dsmobile.core.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public Organization findById(final Integer id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new ValueNotFoundException("Organization not found [id = %s]".formatted(id)));
    }

}
