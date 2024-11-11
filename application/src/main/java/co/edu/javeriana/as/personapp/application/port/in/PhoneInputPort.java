package co.edu.javeriana.as.personapp.application.port.in;

import java.util.List;

import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Phone;

@Port
public interface PhoneInputPort {

    void setPersistence(PhoneOutputPort phonePersistence);

    Phone create(Phone phone);

    Phone edit(String id, Phone phone) throws NoExistException;

    boolean drop(String id) throws NoExistException;

    List<Phone> findAll();

    Phone findOne(String id) throws NoExistException;

    int count();
}
