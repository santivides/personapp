package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mariadb.entity.TelefonoEntity;
import co.edu.javeriana.as.personapp.model.request.StudyRequest;
import co.edu.javeriana.as.personapp.model.response.StudyResponse;
import co.edu.javeriana.as.personapp.mongo.document.TelefonoDocument;
import co.edu.javeriana.as.personapp.model.request.TelefonoRequest;
import co.edu.javeriana.as.personapp.model.response.TelefonoResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class TelefonoMapperRest {

    public TelefonoResponse fromDomainToAdapterRestMaria(Phone phone){
        return fromDomainToAdapterRest(phone, "MariaDB");
    }

    public TelefonoResponse fromDomainToAdapterRestMongo(Phone phone){
        return fromDomainToAdapterRest(phone, "MongoDB");
    }

    public TelefonoResponse fromDomainToAdapterRest(Phone phone, String database){
        return new TelefonoResponse(
                phone.getNumber(),
                phone.getCompany(),
                phone.getOwner().getIdentification().toString(),
                database,
                "Ok");
    }

    public Phone fromAdapterToDomain(TelefonoRequest request, Person owner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return new Phone(
                request.getNumero(),
                request.getCompania(),
                owner
        );
    }
}
