package by.it.dsmobile.core.repository;

import by.it.dsmobile.core.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    List<Group> findAllByClassTeacherId(Integer id);

    List<Group> findByUsers_Id(Integer userId);

    List<Group> findAllByOrganization_Id(Integer id);

}
