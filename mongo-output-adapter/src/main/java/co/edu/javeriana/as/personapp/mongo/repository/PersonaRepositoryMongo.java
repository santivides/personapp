package co.edu.javeriana.as.personapp.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.javeriana.as.personapp.mongo.document.PersonaDocument;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepositoryMongo extends MongoRepository<PersonaDocument, Integer> {

}
