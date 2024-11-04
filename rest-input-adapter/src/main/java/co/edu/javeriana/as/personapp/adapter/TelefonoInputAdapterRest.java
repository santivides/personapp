package co.edu.javeriana.as.personapp.adapter;

import co.edu.javeriana.as.personapp.mapper.TelefonoMapperRest;
import co.edu.javeriana.as.personapp.model.request.TelefonoRequest;
import co.edu.javeriana.as.personapp.model.response.TelefonoResponse;
import co.edu.javeriana.as.personapp.mongo.document.TelefonoDocument;
import co.edu.javeriana.as.personapp.mongo.repository.TelefonoRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelefonoInputAdapterRest {

    @Autowired
    private TelefonoRepositoryMongo telefonoRepository;

    @Autowired
    private TelefonoMapperRest telefonoMapper;

    public TelefonoResponse getTelefonoById(String id) {
        TelefonoDocument document = telefonoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phone not found"));
        return telefonoMapper.toResponse(document);
    }

    public TelefonoResponse createTelefono(TelefonoRequest request) {
        TelefonoDocument document = telefonoMapper.toDocument(request);
        TelefonoDocument savedDocument = telefonoRepository.save(document);
        return telefonoMapper.toResponse(savedDocument);
    }

    public TelefonoResponse updateTelefono(String id, TelefonoRequest request) {
        TelefonoDocument document = telefonoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phone not found"));
        document.setNumero(request.getNumero());
        document.setOper(request.getTipo());
        TelefonoDocument updatedDocument = telefonoRepository.save(document);
        return telefonoMapper.toResponse(updatedDocument);
    }

    public void deleteTelefono(String id) {
        telefonoRepository.deleteById(id);
    }
}
