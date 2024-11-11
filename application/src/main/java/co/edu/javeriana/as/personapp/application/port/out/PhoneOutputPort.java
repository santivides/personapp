package co.edu.javeriana.as.personapp.application.port.out;

import java.util.List;
import java.util.Optional;
import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.domain.Phone;

@Port
public interface PhoneOutputPort {
    Phone save(Phone phone);
    boolean delete(String id);
    List<Phone> findAll();
    Optional<Phone> findById(String id);
}
