package by.it.dsmobile.core.repository;

import by.it.dsmobile.core.model.PassOrder;
import by.it.dsmobile.core.model.PassOrderStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassOrderRepository extends JpaRepository<PassOrder, Integer> {

    long countByUserIdAndPassOrderStatusTypeIsNot(Integer id, PassOrderStatusType status);

    List<PassOrder> findByUserIdAndPassOrderStatusType(Integer id, PassOrderStatusType status);

}
