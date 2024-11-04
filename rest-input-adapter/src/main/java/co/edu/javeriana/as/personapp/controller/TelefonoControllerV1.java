package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.TelefonoInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.TelefonoRequest;
import co.edu.javeriana.as.personapp.model.response.TelefonoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/telefonos")
public class TelefonoControllerV1 {

    @Autowired
    private TelefonoInputAdapterRest telefonoAdapter;

    @GetMapping("/{id}")
    public ResponseEntity<TelefonoResponse> getTelefonoById(@PathVariable String id) {
        return ResponseEntity.ok(telefonoAdapter.getTelefonoById(id));
    }

    @PostMapping
    public ResponseEntity<TelefonoResponse> createTelefono(@RequestBody TelefonoRequest request) {
        return ResponseEntity.ok(telefonoAdapter.createTelefono(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelefonoResponse> updateTelefono(@PathVariable String id, @RequestBody TelefonoRequest request) {
        return ResponseEntity.ok(telefonoAdapter.updateTelefono(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelefono(@PathVariable String id) {
        telefonoAdapter.deleteTelefono(id);
        return ResponseEntity.noContent().build();
    }
}
