package by.it.dsmobile.core.repository;

import by.it.dsmobile.core.model.PassOrder;
import by.it.dsmobile.core.model.PassOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassOrderRepository extends JpaRepository<PassOrder, Integer> {

    long countByUserIdAndStatusIsNot(Integer id, PassOrderStatus status);

    List<PassOrder> findByUserIdAndStatus(Integer id, PassOrderStatus status);

}
