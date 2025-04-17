package by.it.dsmobile.core.repository;

import by.it.dsmobile.core.model.Controller;
import by.it.dsmobile.core.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

    Optional<Device> findFirstByControllerAndAddress(Controller controller, Integer address);

}
