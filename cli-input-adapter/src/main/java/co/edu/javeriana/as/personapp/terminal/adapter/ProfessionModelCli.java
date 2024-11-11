package co.edu.javeriana.as.personapp.terminal.adapter;

import co.edu.javeriana.as.personapp.application.port.in.ProfessionInputPort;
import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PersonUseCase;
import co.edu.javeriana.as.personapp.application.usecase.ProfessionUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.mariadb.adapter.ProfessionOutputAdapterMaria;
import co.edu.javeriana.as.personapp.mongo.adapter.ProfessionOutputAdapterMongo;
import co.edu.javeriana.as.personapp.terminal.mapper.ProfessionMapperCli;
import co.edu.javeriana.as.personapp.terminal.model.PersonaModelCli;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter
public class ProfessionModelCli {

    @Autowired
    @Qualifier("ProfessionOutputAdapterMaria")
    private ProfessionOutputPort professionOutputPortMaria;

    @Autowired
    @Qualifier("ProfessionOutputAdapterMongo")
    private ProfessionOutputPort professionOutputPortMongo;

    @Autowired
    private ProfessionMapperCli professionMapperCli;

    @Autowired
    private ProfessionInputPort professionInputPort;

    public void setpProfessionOutputPortInjection(String dbOption) throws InvalidOptionException {
        if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
            professionInputPort = new ProfessionUseCase(professionOutputPortMaria);
        } else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
            professionInputPort = new ProfessionUseCase(professionOutputPortMongo);
        } else {
            throw new InvalidOptionException("Invalid database option: " + dbOption);
        }
    }

    public void historial() {
        log.info("Into historial PersonaEntity in Input Adapter");
        professionInputPort.findAll().stream()
                .map(professionMapperCli::fromDomainToAdapterCli)
                .forEach(System.out::println);
    }

}
