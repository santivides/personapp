package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.TelefonoInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.TelefonoRequest;
import co.edu.javeriana.as.personapp.model.response.TelefonoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RestController
@RequestMapping("/api/v1/telefono")
public class TelefonoControllerV1 {

    @Autowired
    private TelefonoInputAdapterRest telefonoInputAdapterRest;

    @ResponseBody
    @GetMapping(path = "/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TelefonoResponse> telefonos(@PathVariable String database) {
        log.info("Into telefonos REST API");
        return telefonoInputAdapterRest.Historial(database.toUpperCase());
    }

    @ResponseBody
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TelefonoResponse crearTelefono(@RequestBody TelefonoRequest request) {
        log.info("\"Into telefonos REST API");
        return telefonoInputAdapterRest.CreateTelefono(request);
    }

    @ResponseBody
    @GetMapping(path = "/{database}/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TelefonoResponse obtenerTelefono(@PathVariable String database, @PathVariable String number) {
        log.info("\"Into telefonos REST API");
        return telefonoInputAdapterRest.GetTelefono(database.toUpperCase(), number);
    }

    @ResponseBody
    @PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TelefonoResponse editarTelefono(@RequestBody TelefonoRequest request) {
        log.info("\"Into telefonos REST API");
        return telefonoInputAdapterRest.EditTelefono(request);
    }

    @ResponseBody
    @DeleteMapping(path = "/{database}/{number}")
    public Boolean eliminarTelefono(@PathVariable String database, @PathVariable String number) {
        log.info("\"Into telefonos REST API");
        return telefonoInputAdapterRest.DeleteTelefono(database, number);
    }
}
