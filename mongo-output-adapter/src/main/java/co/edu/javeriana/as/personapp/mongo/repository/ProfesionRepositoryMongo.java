package co.edu.javeriana.as.personapp.mongo.repository;

import co.edu.javeriana.as.personapp.mongo.document.ProfesionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionRepositoryMongo extends MongoRepository<ProfesionDocument, Integer> {
}
