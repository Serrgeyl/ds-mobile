package by.it.dsmobile.core.repository;

import by.it.dsmobile.core.model.User;
import by.it.dsmobile.core.model.UserLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLinkRepository extends JpaRepository<UserLink, Integer> {

    Optional<UserLink> findFirstByUserAndOwner(User user, User owner);

}
