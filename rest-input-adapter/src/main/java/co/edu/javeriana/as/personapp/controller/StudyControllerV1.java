package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.StudyInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.StudyRequest;
import co.edu.javeriana.as.personapp.model.response.StudyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/estudio")
public class StudyControllerV1 {

    @Autowired
    private StudyInputAdapterRest studyInputAdapterRest;

    @ResponseBody
    @GetMapping(path = "/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudyResponse> studies(@PathVariable String database) {
        log.info("Into estudios REST API");
        return studyInputAdapterRest.historial(database.toUpperCase());
    }

    @ResponseBody
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudyRequest crearEstudio(@RequestBody StudyRequest request) {
        log.info("esta en el metodo crearEstudios en el controller del api");
        return studyInputAdapterRest.crearEstudio(request);
    }

    @ResponseBody
    @GetMapping(path = "/{database}/{personCC}/{profesionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudyRequest obtenerPersona(@PathVariable String database, @PathVariable Integer personCC, @PathVariable Integer profesionId) {
        log.info("esta en el metodo obtenerEstudios en el controller del api");
        return studyInputAdapterRest.obtenerEstudio(database.toUpperCase(), Integer.toString(personCC), Integer.toString(profesionId));
    }

    @ResponseBody
    @PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudyRequest editarPersona(@RequestBody StudyRequest request) {
        log.info("esta en el metodo editarPersona en el controller del api");
        return studyInputAdapterRest.editarEstudio(request);
    }

    @ResponseBody
    @DeleteMapping(path = "/{database}/{personCC}/{profesionId}")
    public Boolean eliminarPersona(@PathVariable String database, @PathVariable Integer personCC, @PathVariable Integer profesionId) {
        log.info("esta en el metodo eliminarPersona en el controller del api");
        return studyInputAdapterRest.eliminarEstudio(database.toUpperCase(), personCC, profesionId);
    }
}
