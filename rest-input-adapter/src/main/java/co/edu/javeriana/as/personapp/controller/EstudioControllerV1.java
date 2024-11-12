package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.EstudioInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.EstudioRequest;
import co.edu.javeriana.as.personapp.model.request.EstudioRequest;
import co.edu.javeriana.as.personapp.model.response.EstudioResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/estudio")
public class EstudioControllerV1 {

    @Autowired
    private EstudioInputAdapterRest estudioInputAdapterRest;

    @ResponseBody
    @GetMapping(path = "/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EstudioResponse> studies(@PathVariable String database) {
        log.info("Into estudios REST API");
        return estudioInputAdapterRest.historial(database.toUpperCase());
    }

    @ResponseBody
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EstudioResponse crearEstudio(@RequestBody EstudioRequest request) {
        log.info("esta en el metodo crearEstudios en el controller del api");
        return estudioInputAdapterRest.crearEstudio(request);
    }

    @ResponseBody
    @GetMapping(path = "/{database}/{personCC}/{profesionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EstudioResponse obtenerPersona(@PathVariable String database, @PathVariable Integer personCC, @PathVariable Integer profesionId) {
        log.info("esta en el metodo obtenerEstudios en el controller del api");
        return estudioInputAdapterRest.obtenerEstudio(database.toUpperCase(), Integer.toString(personCC), Integer.toString(profesionId));
    }

    @ResponseBody
    @PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EstudioResponse editarPersona(@RequestBody EstudioRequest request) {
        log.info("esta en el metodo editarPersona en el controller del api");
        return estudioInputAdapterRest.editarEstudio(request);
    }

    @ResponseBody
    @DeleteMapping(path = "/{database}/{personCC}/{profesionId}")
    public Boolean eliminarPersona(@PathVariable String database, @PathVariable Integer personCC, @PathVariable Integer profesionId) {
        log.info("esta en el metodo eliminarPersona en el controller del api");
        return estudioInputAdapterRest.eliminarEstudio(database.toUpperCase(), personCC, profesionId);
    }
}
