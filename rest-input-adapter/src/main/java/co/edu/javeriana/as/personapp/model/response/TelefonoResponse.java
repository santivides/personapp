package co.edu.javeriana.as.personapp.model.response;
import co.edu.javeriana.as.personapp.model.request.TelefonoRequest;

public class TelefonoResponse {
    private String id;
    private String numero;
    private String tipo;
    private String personaId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }
}
