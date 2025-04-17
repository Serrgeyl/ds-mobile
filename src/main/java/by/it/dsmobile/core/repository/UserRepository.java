package by.it.dsmobile.core.repository;

import by.it.dsmobile.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByPhoneNumberAndSecurityCodeExpirationDateIsAfter(String phoneNumber, OffsetDateTime expirationDate);

    @Query(value = "select u.balance from users u where u.id = :id", nativeQuery = true)
    Optional<Double> getBalanceById(final Integer id);

}
