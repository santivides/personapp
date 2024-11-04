package co.edu.javeriana.as.personapp.model.request;
import co.edu.javeriana.as.personapp.domain.Phone;


public class TelefonoRequest {
    private String numero;
    private String tipo;
    private String personaId;

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

    // Método para convertir a dominio si es necesario
    public Phone toDomain() {
        Phone phone = new Phone();
        phone.setNumber(this.numero);
        phone.setCompany(this.tipo);
        return phone;
    }
}
