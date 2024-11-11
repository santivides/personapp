package co.edu.javeriana.as.personapp.mongo.adapter;

import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.mongo.document.TelefonoDocument;
import co.edu.javeriana.as.personapp.mongo.mapper.TelefonoMapperMongo;
import co.edu.javeriana.as.personapp.mongo.repository.TelefonoRepositoryMongo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component("phoneOutputAdapterMongo")
public class PhoneOutputAdapterMongo implements PhoneOutputPort {

    @Autowired
    private TelefonoRepositoryMongo telefonoRepositoryMongo;

    @Autowired
    private TelefonoMapperMongo telefonoMapperMongo;

    @Override
    public Phone save(Phone phone) {
        log.debug("Saving phone in MongoDB adapter");
        TelefonoDocument document = telefonoMapperMongo.fromDomainToAdapter(phone);
        TelefonoDocument savedDocument = telefonoRepositoryMongo.save(document);
        return telefonoMapperMongo.fromAdapterToDomain(savedDocument);
    }

    @Override
    public boolean delete(String id) {
        log.debug("Deleting phone in MongoDB adapter");
        telefonoRepositoryMongo.deleteById(id);
        return !telefonoRepositoryMongo.findById(id).isPresent();
    }

    @Override
    public Optional<Phone> findById(String id) {
        log.debug("Finding phone by ID in MongoDB adapter");
        Optional<TelefonoDocument> document = telefonoRepositoryMongo.findById(id);
        return document.map(telefonoMapperMongo::fromAdapterToDomain);
    }

    @Override
    public List<Phone> findAll() {
        log.debug("Finding all phones in MongoDB adapter");
        return telefonoRepositoryMongo.findAll().stream()
                .map(telefonoMapperMongo::fromAdapterToDomain)
                .collect(Collectors.toList());
    }
}
