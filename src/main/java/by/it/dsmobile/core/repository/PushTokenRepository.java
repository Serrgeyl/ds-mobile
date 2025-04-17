package by.it.dsmobile.core.repository;

import by.it.dsmobile.core.model.PushToken;
import by.it.dsmobile.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PushTokenRepository extends JpaRepository<PushToken, Integer> {

    Optional<PushToken> findFirstByUser(User user);

}
