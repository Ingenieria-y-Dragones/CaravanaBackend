package co.edu.javeriana.caravana.controller;

import co.edu.javeriana.caravana.dto.CaravanaDTO;
import co.edu.javeriana.caravana.dto.JugadorDTO;
import co.edu.javeriana.caravana.dto.RutaDTO;
import co.edu.javeriana.caravana.model.Rol;
import co.edu.javeriana.caravana.service.JugadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/jugador")
public class JugadorController {
    @Autowired
    private JugadorService jugadorService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /*
    @Secured({Rol.Codigo.CARAVANERO, Rol.Codigo.COMERCIANTE})
    @GetMapping("{emailJugador}")
    public JugadorDTO buscarJugador(@PathVariable("emailJugador") String email) {
        logger.info("Vista de jugador");
        return jugadorService.buscarJugador(email).orElseThrow();
    }
    */

    @Secured({Rol.Codigo.CARAVANERO, Rol.Codigo.COMERCIANTE})
    @GetMapping("{emailJugador}")
    public CaravanaDTO buscarCaravana(@PathVariable("emailJugador") String email) {
        logger.info("Vista de caravana");
        return jugadorService.buscarCaravana(email).orElseThrow();
    }
}
