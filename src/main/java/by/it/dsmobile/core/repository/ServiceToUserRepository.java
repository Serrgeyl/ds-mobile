package by.it.dsmobile.core.repository;

import by.it.dsmobile.core.model.ServiceToUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceToUserRepository extends JpaRepository<ServiceToUser, Integer> {

    List<ServiceToUser> findByOwnerId(Integer ownerId);

    Optional<ServiceToUser> findFirstByOwnerIdAndUserId(Integer ownerId, Integer userId);

}
