package co.edu.javeriana.as.personapp.adapter;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.in.ProfessionInputPort;
import co.edu.javeriana.as.personapp.application.port.in.StudyInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PersonUseCase;
import co.edu.javeriana.as.personapp.application.usecase.StudyUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mapper.StudyMapperRest;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import co.edu.javeriana.as.personapp.model.request.StudyRequest;
import co.edu.javeriana.as.personapp.model.response.PersonaResponse;
import co.edu.javeriana.as.personapp.model.response.StudyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter
public class StudyInputAdapterRest {

    @Autowired
    @Qualifier("studyOutputAdapterMaria")
    private StudyOutputPort studyOutputPortMaria;

    @Autowired
    @Qualifier("studyOutputAdapterMongo")
    private StudyOutputPort studyOutputAdapterMongo;

    @Autowired
    private StudyMapperRest studyMapperRest;

    @Autowired
    private PersonInputPort personInputPort;

    @Autowired
    private ProfessionInputPort professionInputPort;

    @Autowired
    private StudyInputPort studyInputPort;

    private String setStudyOutputPortInjection(String dbOption) throws InvalidOptionException {
        if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
            studyInputPort = new StudyUseCase(studyOutputPortMaria);
            return DatabaseOption.MARIA.toString();
        } else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
            studyInputPort = new StudyUseCase(studyOutputAdapterMongo);
            return  DatabaseOption.MONGO.toString();
        } else {
            throw new InvalidOptionException("Invalid database option: " + dbOption);
        }
    }

    public List<StudyResponse> historial(String database) {
        log.info("Into historial EstudyEntity in Input Adapter");
        try {
            if (setStudyOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
                return studyInputPort.findAll().stream()
                        .map(studyMapperRest::fromDomainToAdapterRestMaria)
                        .collect(Collectors.toList());
            } else {
                return studyInputPort.findAll().stream()
                        .map(studyMapperRest::fromDomainToAdapterRestMongo)
                        .collect(Collectors.toList());
            }
        } catch (InvalidOptionException e) {
            log.warn(e.getMessage());
            return new ArrayList<>();
        }
    }

    public StudyResponse crearEstudio(StudyRequest request) {
        try {
            setStudyOutputPortInjection(request.getDatabase());
            Person person = personInputPort.findOne(Integer.valueOf(request.getPersonCC()));
            Profession profession = professionInputPort.findOne(Integer.valueOf(request.getProfessionId()));
            Study study = studyInputPort.create(studyMapperRest.fromAdapterToDomain(request, person, profession));
            return studyMapperRest.fromDomainToAdapterRest(study, request.getDatabase());
        } catch (InvalidOptionException | NoExistException e) {
            log.warn(e.getMessage());
            //return new PersonaResponse("", "", "", "", "", "", "");
        }
        return null;
    }

    public StudyResponse obtenerEstudio(String database, String personCC, String profesionId) {
        try {
            if(setStudyOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
                return studyMapperRest.fromDomainToAdapterRestMaria(studyInputPort.findOne(Integer.valueOf(personCC), Integer.valueOf(profesionId)));
            }else {
                return studyMapperRest.fromDomainToAdapterRestMongo(studyInputPort.findOne(Integer.valueOf(personCC), Integer.valueOf(profesionId)));
            }
        } catch (InvalidOptionException | NoExistException e) {
            log.warn(e.getMessage());
            return new StudyResponse("", "", "", "", "", "");
        }
    }

    public StudyResponse editarEstudio(StudyRequest request) {
        try {
            String database = setStudyOutputPortInjection(request.getDatabase());
            Person person = personInputPort.findOne(Integer.valueOf(request.getPersonCC()));
            Profession profession = professionInputPort.findOne(Integer.valueOf(request.getProfessionId()));
            Study study = studyInputPort.edit(Integer.parseInt(request.getPersonCC()),
                    Integer.valueOf(request.getProfessionId()), studyMapperRest.fromAdapterToDomain(request, person, profession));
            if(database.equalsIgnoreCase(DatabaseOption.MARIA.toString())){
                return studyMapperRest.fromDomainToAdapterRestMaria(study);
            }else {
                return studyMapperRest.fromDomainToAdapterRestMongo(study);
            }
        } catch (InvalidOptionException | NumberFormatException | NoExistException e) {
            log.warn(e.getMessage());
            return new StudyResponse("", "", "", "", "", "");
        }
    }

    public Boolean eliminarEstudio(String database, Integer personCC, Integer profesionId) {
        log.info("Into EliminarEstudios EstudiosEntity in  Adapter");
        try {
            setStudyOutputPortInjection(database);
            return studyInputPort.drop(Integer.valueOf(personCC), Integer.valueOf(profesionId));
        } catch (InvalidOptionException | NoExistException e) {
            log.warn(e.getMessage());
            return false;
        }
    }

}
