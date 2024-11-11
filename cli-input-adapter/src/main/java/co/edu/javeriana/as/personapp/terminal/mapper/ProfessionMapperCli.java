package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.terminal.model.ProfessionModelCli;

public class ProfessionMapperCli {

    public ProfessionModelCli fromDomainToAdapterCli(Profession profession){
        ProfessionModelCli professionModelCli = new ProfessionModelCli();
        professionModelCli.setIdentification(profession.getIdentification());
        professionModelCli.setName(profession.getName());
        professionModelCli.setDescription(profession.getDescription());
        return professionModelCli;
    }
}
