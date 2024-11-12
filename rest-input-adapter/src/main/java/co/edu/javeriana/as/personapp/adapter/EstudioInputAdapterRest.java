package co.edu.javeriana.as.personapp.adapter;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.in.ProfessionInputPort;
import co.edu.javeriana.as.personapp.application.port.in.StudyInputPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.StudyUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mapper.EstudioMapperRest;
import co.edu.javeriana.as.personapp.model.request.EstudioRequest;
import co.edu.javeriana.as.personapp.model.response.EstudioResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter
public class EstudioInputAdapterRest {

    @Autowired
    @Qualifier("studyOutputAdapterMaria")
    private StudyOutputPort studyOutputPortMaria;

    @Autowired
    @Qualifier("studyOutputAdapterMongo")
    private StudyOutputPort studyOutputAdapterMongo;

    @Autowired
    private EstudioMapperRest estudioMapperRest;

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

    public List<EstudioResponse> historial(String database) {
        log.info("Into historial EstudyEntity in Input Adapter");
        try {
            if (setStudyOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
                return studyInputPort.findAll().stream()
                        .map(estudioMapperRest::fromDomainToAdapterRestMaria)
                        .collect(Collectors.toList());
            } else {
                return studyInputPort.findAll().stream()
                        .map(estudioMapperRest::fromDomainToAdapterRestMongo)
                        .collect(Collectors.toList());
            }
        } catch (InvalidOptionException e) {
            log.warn(e.getMessage());
            return new ArrayList<>();
        }
    }

    public EstudioResponse crearEstudio(EstudioRequest request) {
        try {
            setStudyOutputPortInjection(request.getDatabase());
            Person person = personInputPort.findOne(Integer.valueOf(request.getPersonCC()));
            Profession profession = professionInputPort.findOne(Integer.valueOf(request.getProfessionId()));
            Study study = studyInputPort.create(estudioMapperRest.fromAdapterToDomain(request, person, profession));
            return estudioMapperRest.fromDomainToAdapterRest(study, request.getDatabase());
        } catch (InvalidOptionException | NoExistException e) {
            log.warn(e.getMessage());
            //return new PersonaResponse("", "", "", "", "", "", "");
        }
        return null;
    }

    public EstudioResponse obtenerEstudio(String database, String personCC, String profesionId) {
        try {
            if(setStudyOutputPortInjection(database).equalsIgnoreCase(DatabaseOption.MARIA.toString())){
                return estudioMapperRest.fromDomainToAdapterRestMaria(studyInputPort.findOne(Integer.valueOf(personCC), Integer.valueOf(profesionId)));
            }else {
                return estudioMapperRest.fromDomainToAdapterRestMongo(studyInputPort.findOne(Integer.valueOf(personCC), Integer.valueOf(profesionId)));
            }
        } catch (InvalidOptionException | NoExistException e) {
            log.warn(e.getMessage());
            return new EstudioResponse("", "", "", "", "", "");
        }
    }

    public EstudioResponse editarEstudio(EstudioRequest request) {
        try {
            String database = setStudyOutputPortInjection(request.getDatabase());
            Person person = personInputPort.findOne(Integer.valueOf(request.getPersonCC()));
            Profession profession = professionInputPort.findOne(Integer.valueOf(request.getProfessionId()));
            Study study = studyInputPort.edit(Integer.parseInt(request.getPersonCC()),
                    Integer.valueOf(request.getProfessionId()), estudioMapperRest.fromAdapterToDomain(request, person, profession));
            if(database.equalsIgnoreCase(DatabaseOption.MARIA.toString())){
                return estudioMapperRest.fromDomainToAdapterRestMaria(study);
            }else {
                return estudioMapperRest.fromDomainToAdapterRestMongo(study);
            }
        } catch (InvalidOptionException | NumberFormatException | NoExistException e) {
            log.warn(e.getMessage());
            return new EstudioResponse("", "", "", "", "", "");
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
