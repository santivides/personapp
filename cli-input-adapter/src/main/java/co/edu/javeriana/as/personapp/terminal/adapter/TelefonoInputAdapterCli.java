package co.edu.javeriana.as.personapp.terminal.adapter;

import co.edu.javeriana.as.personapp.application.port.in.PhoneInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PhoneUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.terminal.mapper.TelefonoMapperCli;
import co.edu.javeriana.as.personapp.terminal.model.TelefonoModelCli;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter
public class TelefonoInputAdapterCli {

    @Autowired
    @Qualifier("phoneOutputAdapterMaria")
    private PhoneOutputPort phoneOutputPortMaria;

    @Autowired
    @Qualifier("phoneOutputAdapterMongo")
    private PhoneOutputPort phoneOutputPortMongo;

    @Autowired
    private TelefonoMapperCli telefonoMapperCli;

    private PhoneInputPort phoneInputPort;

    public void setTelefonoOutputPortInjection(String dbOption) throws InvalidOptionException {
        if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
            phoneInputPort = new PhoneUseCase(phoneOutputPortMaria);
        } else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
            phoneInputPort = new PhoneUseCase(phoneOutputPortMongo);
        } else {
            throw new InvalidOptionException("Invalid database option: " + dbOption);
        }
    }

    public void historial() {
        log.info("Into historial TelefonoEntity in Input Adapter");
        List<TelefonoModelCli> telefonos = phoneInputPort.findAll().stream()
                .map(telefonoMapperCli::fromDomainToAdapterCli)
                .collect(Collectors.toList());
        telefonos.forEach(System.out::println);
    }

    public void createPhone(Phone phone) {
        log.info("Creating new phone in Input Adapter");
        Phone createdPhone = phoneInputPort.create(phone);
        System.out.println("Phone created: " + telefonoMapperCli.fromDomainToAdapterCli(createdPhone));
    }

    public void editPhone(String id, Phone phone) {
        log.info("Editing phone in Input Adapter");
        try {
            Phone editedPhone = phoneInputPort.edit(id, phone);
            System.out.println("Phone updated: " + telefonoMapperCli.fromDomainToAdapterCli(editedPhone));
        } catch (NoExistException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePhone(String id) {
        log.info("Deleting phone in Input Adapter");
        try {
            boolean isDeleted = phoneInputPort.drop(id);
            if (isDeleted) {
                System.out.println("Phone deleted successfully");
            } else {
                System.out.println("Failed to delete phone");
            }
        } catch (NoExistException e) {
            System.out.println(e.getMessage());
        }
    }
}
