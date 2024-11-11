package co.edu.javeriana.as.personapp.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import co.edu.javeriana.as.personapp.mongo.document.TelefonoDocument;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefonoRepositoryMongo extends MongoRepository<TelefonoDocument, String> {
}
