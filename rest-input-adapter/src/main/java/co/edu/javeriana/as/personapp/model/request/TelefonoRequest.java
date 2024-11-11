package co.edu.javeriana.as.personapp.model.request;
import co.edu.javeriana.as.personapp.domain.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelefonoRequest {
    private String numero;
    private String compania;
    private String personaId;
    private String database;
}
