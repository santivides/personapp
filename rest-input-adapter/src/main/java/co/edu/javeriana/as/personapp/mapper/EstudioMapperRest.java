package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.model.request.EstudioRequest;
import co.edu.javeriana.as.personapp.model.response.EstudioResponse;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper
public class EstudioMapperRest {

    public EstudioResponse fromDomainToAdapterRestMaria(Study study){
        return fromDomainToAdapterRest(study, "MariaDB");
    }

    public EstudioResponse fromDomainToAdapterRestMongo(Study study){
        return fromDomainToAdapterRest(study, "MongoDB");
    }

    public EstudioResponse fromDomainToAdapterRest(Study study, String database){
        return new EstudioResponse(
                study.getPerson().getIdentification()+"",
                study.getProfession().getIdentification()+"",
                study.getGraduationDate()+"",
                study.getUniversityName(),
                database,
                "Ok");
    }

    public Study fromAdapterToDomain(EstudioRequest request, Person person, Profession profession) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return new Study(person,
                profession,
                LocalDate.parse(request.getGraduationDate(), formatter), request.getUniversityName());
    }
}
