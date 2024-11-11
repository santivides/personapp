package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.ProfesionInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.ProfesionRequest;
import co.edu.javeriana.as.personapp.model.response.ProfesionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/profesion")
public class ProfesionControllerV1 {
    @Autowired
    private ProfesionInputAdapterRest profesionInputAdapterRest;

    @ResponseBody
    @GetMapping(path = "/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProfesionResponse> profesiones(@PathVariable String database) {
        log.info("Into profesion REST API");
        return profesionInputAdapterRest.Historial(database.toUpperCase());
    }

    @ResponseBody
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProfesionResponse crearProfesion(@RequestBody ProfesionRequest request) {
        log.info("esta en el metodo crearProfesion en el controller del api");
        return profesionInputAdapterRest.CreateProfesion(request);
    }

    @ResponseBody
    @GetMapping(path = "/{database}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProfesionResponse obtenerProfesion(@PathVariable String database, @PathVariable Integer id) {
        log.info("esta en el metodo obtenerProfesion en el controller del api");
        return profesionInputAdapterRest.GetProfesion(database.toUpperCase(), id);
    }

    @ResponseBody
    @PutMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProfesionResponse editarProfesion(@RequestBody ProfesionRequest request) {
        log.info("esta en el metodo editarProfesion en el controller del api");
        return profesionInputAdapterRest.EditProfesion(request);
    }

    @ResponseBody
    @DeleteMapping(path = "/{database}/{id}")
    public Boolean eliminarProfesion(@PathVariable String database, @PathVariable Integer id) {
        log.info("esta en el metodo eliminarProfesion en el controller del api");
        return profesionInputAdapterRest.DeleterProfesion(database, id);
    }

}
