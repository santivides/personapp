package co.edu.javeriana.as.personapp.model.response;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.model.request.TelefonoRequest;

public class TelefonoResponse extends TelefonoRequest {

    private String status;

    public  TelefonoResponse (String numero, String compania, String personaId, String database, String status){
        super(numero, compania, personaId, database);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
