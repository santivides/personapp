package co.edu.javeriana.as.personapp.terminal.adapter;

import co.edu.javeriana.as.personapp.application.port.in.StudyInputPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PersonUseCase;
import co.edu.javeriana.as.personapp.application.usecase.StudyUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.terminal.mapper.EstudioMapperCli;
import co.edu.javeriana.as.personapp.terminal.model.EstudioModelCli;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter
public class EstudioInputAdapterCli {

    @Autowired
    @Qualifier("studyOutputAdapterMaria")
    private StudyOutputPort studyOutputPortMaria;

    @Autowired
    @Qualifier("studyOutputAdapterMongo")
    private StudyOutputPort studyOutputPortMongo;

    @Autowired
    private EstudioMapperCli estudioMapperCli;

    StudyInputPort estudyInputPort;

    public void setStudyOutputPortInjection(String dbOption) throws InvalidOptionException {
        if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
            estudyInputPort = new StudyUseCase(studyOutputPortMaria);
        } else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
            estudyInputPort = new StudyUseCase(studyOutputPortMongo);
        } else {
            throw new InvalidOptionException("Invalid database option: " + dbOption);
        }
    }

    public void historial1() {
        log.info("Into historial PersonaEntity in Input Adapter");
        List<EstudioModelCli> estudio = estudyInputPort.findAll().stream().map(estudioMapperCli::fromDomainToAdapterCli)
                .collect(Collectors.toList());
        estudio.forEach(p -> System.out.println(p.toString()));
    }
    public void historial() {
        log.info("Into historial PersonaEntity in Input Adapter");
        estudyInputPort.findAll().stream()
                .map(estudioMapperCli::fromDomainToAdapterCli)
                .forEach(System.out::println);
    }
}
