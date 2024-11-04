package co.edu.javeriana.as.personapp.terminal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelefonoModelCli {
    private String numero;          // Equivalente a "number" en Phone
    private String operadora;       // Equivalente a "company" en Phone
    private Integer duenioId;       // Para el ID del dueño (Person)
    private String duenioNombre;    // Nombre completo del dueño (Person)
}
