package by.it.dsmobile.core.repository.specification;

import by.it.dsmobile.core.model.Service;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ServiceSpecification {

    public static Specification<Service> hasDisposable(Boolean disposable) {
        return disposable == null
                ? (root, query, builder) -> builder.conjunction()
                : (root, query, builder) -> builder.equal(root.get("disposable"), disposable);
    }

}
