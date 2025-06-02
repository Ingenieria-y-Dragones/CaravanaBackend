package co.edu.javeriana.caravana.controller;

import co.edu.javeriana.caravana.dto.RutaDTO;
import co.edu.javeriana.caravana.dto.RutaDestinoDTO;
import co.edu.javeriana.caravana.model.Rol;
import co.edu.javeriana.caravana.service.RutaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/ruta")
public class RutaController {

    @Autowired
    private RutaService rutaService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Secured({Rol.Codigo.CARAVANERO, Rol.Codigo.COMERCIANTE})
    @PostMapping
    public RutaDTO crearRuta(@RequestBody RutaDTO rutaDTO) {
        logger.info("Crear ruta");
        return rutaService.crearRuta(rutaDTO);
    }

    @Secured({Rol.Codigo.CARAVANERO, Rol.Codigo.COMERCIANTE})
    @GetMapping("{idRuta}")
    public RutaDTO buscarRuta(@PathVariable("idRuta") Long id) {
        logger.info("Vista de ruta");
        return rutaService.buscarRuta(id).orElseThrow();
    }

    @Secured({Rol.Codigo.CARAVANERO, Rol.Codigo.COMERCIANTE})
    @PutMapping
    public RutaDTO actualizarRuta(@RequestBody RutaDTO rutaDTO) {
        logger.info("Actualizar ruta");
        return rutaService.actualizarRuta(rutaDTO);
    }

    @Secured({Rol.Codigo.CARAVANERO, Rol.Codigo.COMERCIANTE})
    @DeleteMapping("{idRuta}")
    public void eliminarRuta(@PathVariable("idRuta") Long id) {
        logger.info("Eliminar ruta");
        rutaService.eliminarRuta(id);
    }

    @Secured({Rol.Codigo.CARAVANERO, Rol.Codigo.COMERCIANTE})
    @GetMapping("/lista")
    public List<RutaDTO> listarRutas() {
        logger.info("Lista de rutas");
        return rutaService.listarRutas();
    }

    @Secured({Rol.Codigo.CARAVANERO, Rol.Codigo.COMERCIANTE})
    @GetMapping("{idCaravana}/lista")
    public List<RutaDestinoDTO> recuperarRutasDesdeCiudadDeCaravana(@PathVariable("idCaravana") Long id) {
        logger.info("Lista de rutas desde ciudad de caravana");
        return rutaService.recuperarRutasDesdeCiudadDeCaravana(id).orElseThrow();
    }
}

