package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.terminal.model.TelefonoModelCli;

@Mapper
public class TelefonoMapperCli {

    public TelefonoModelCli fromDomainToAdapterCli(Phone phone) {
        TelefonoModelCli telefonoModelCli = new TelefonoModelCli();
        telefonoModelCli.setNumero(phone.getNumber());                     // Mapear número
        telefonoModelCli.setOperadora(phone.getCompany());                 // Mapear compañía
        telefonoModelCli.setDuenioId(phone.getOwner().getIdentification()); // ID del dueño
        telefonoModelCli.setDuenioNombre(phone.getOwner().getFirstName() + " " + phone.getOwner().getLastName()); // Nombre del dueño
        return telefonoModelCli;
    }
}
