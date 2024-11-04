package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.mariadb.entity.TelefonoEntity;
import co.edu.javeriana.as.personapp.mongo.document.TelefonoDocument;
import co.edu.javeriana.as.personapp.model.request.TelefonoRequest;
import co.edu.javeriana.as.personapp.model.response.TelefonoResponse;
import org.springframework.stereotype.Component;

@Component
public class TelefonoMapperRest {

    public TelefonoEntity toEntity(TelefonoRequest request) {
        TelefonoEntity telefonoEntity = new TelefonoEntity();
        telefonoEntity.setNum(request.getNumero());
        telefonoEntity.setOper(request.getTipo());
        return telefonoEntity;
    }

    public TelefonoDocument toDocument(TelefonoRequest request) {
        TelefonoDocument telefonoDocument = new TelefonoDocument();
        telefonoDocument.setNumero(request.getNumero());
        telefonoDocument.setOper(request.getTipo());
        return telefonoDocument;
    }

    public TelefonoResponse toResponse(TelefonoEntity entity) {
        TelefonoResponse response = new TelefonoResponse();
        response.setId(entity.getNum());
        response.setNumero(entity.getNum());
        response.setTipo(entity.getOper());
        response.setPersonaId(entity.getDuenio() != null ? String.valueOf(entity.getDuenio().getCc()) : null);
        return response;
    }

    public TelefonoResponse toResponse(TelefonoDocument document) {
        TelefonoResponse response = new TelefonoResponse();
        response.setId(document.getId());
        response.setNumero(document.getNumero());
        response.setTipo(document.getOper());
        response.setPersonaId(document.getPrimaryDuenio() != null ? document.getPrimaryDuenio().getId().toString() : null);
        return response;
    }
}
