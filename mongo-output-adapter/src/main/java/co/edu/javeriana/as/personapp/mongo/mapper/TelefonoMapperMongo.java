package co.edu.javeriana.as.personapp.mongo.mapper;

import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.mongo.document.PersonaDocument;
import co.edu.javeriana.as.personapp.mongo.document.TelefonoDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelefonoMapperMongo {

	@Autowired
	private PersonaMapperMongo personaMapperMongo;

	public TelefonoDocument fromDomainToAdapter(Phone phone) {
		TelefonoDocument telefonoDocument = new TelefonoDocument();
		telefonoDocument.setId(phone.getNumber());
		telefonoDocument.setNumero(phone.getNumber());
		telefonoDocument.setOper(phone.getCompany());
		telefonoDocument.setPrimaryDuenio(personaMapperMongo.fromDomainToAdapter(phone.getOwner()));
		return telefonoDocument;
	}

	public Phone fromAdapterToDomain(TelefonoDocument telefonoDocument) {
		Phone phone = new Phone();
		phone.setNumber(telefonoDocument.getNumero());
		phone.setCompany(telefonoDocument.getOper());
		phone.setOwner(personaMapperMongo.fromAdapterToDomain(telefonoDocument.getPrimaryDuenio()));
		return phone;
	}
}
