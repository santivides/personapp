package co.edu.javeriana.as.personapp.application.usecase;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.PhoneInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.UseCase;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Phone;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UseCase
public class PhoneUseCase implements PhoneInputPort {

    private PhoneOutputPort phonePersistence;

    public PhoneUseCase(@Qualifier("phoneOutputAdapter") PhoneOutputPort phonePersistence) {
        this.phonePersistence = phonePersistence;
    }

    @Override
    public void setPersistence(PhoneOutputPort phonePersistence) {
        this.phonePersistence = phonePersistence;
    }

    @Override
    public Phone create(Phone phone) {
        log.debug("Creating phone in Application Domain");
        return phonePersistence.save(phone);
    }

    @Override
    public Phone edit(String id, Phone phone) throws NoExistException {
        Optional<Phone> existingPhone = phonePersistence.findById(id);
        if (existingPhone.isPresent()) {
            return phonePersistence.save(phone);
        } else {
            throw new NoExistException("The phone with id " + id + " does not exist in the database, cannot be edited");
        }
    }

    @Override
    public boolean drop(String id) throws NoExistException {
        Optional<Phone> existingPhone = phonePersistence.findById(id);
        if (existingPhone.isPresent()) {
            return phonePersistence.delete(id);
        } else {
            throw new NoExistException("The phone with id " + id + " does not exist in the database, cannot be deleted");
        }
    }

    @Override
    public List<Phone> findAll() {
        log.info("Output: " + phonePersistence.getClass());
        return phonePersistence.findAll();
    }

    @Override
    public Phone findOne(String id) throws NoExistException {
        Optional<Phone> phone = phonePersistence.findById(id);
        if (phone.isPresent()) {
            return phone.get();
        } else {
            throw new NoExistException("The phone with id " + id + " does not exist in the database, cannot be found");
        }
    }

    @Override
    public int count() {
        return findAll().size();
    }
}
