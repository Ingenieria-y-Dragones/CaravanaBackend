package co.edu.javeriana.caravana.controller;

import co.edu.javeriana.caravana.dto.CaravanaDTO;
import co.edu.javeriana.caravana.dto.CiudadDTO;
import co.edu.javeriana.caravana.dto.InventarioCaravanaDTO;
import co.edu.javeriana.caravana.dto.JugadorDTO;
import co.edu.javeriana.caravana.model.Juego;
import co.edu.javeriana.caravana.model.Jugador;
import co.edu.javeriana.caravana.repository.CaravanaRepository;
import co.edu.javeriana.caravana.service.CaravanaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/caravana")
public class CaravanaController {

    @Autowired
    private CaravanaService caravanaService;

    @Autowired
    private CaravanaRepository caravanaRepository;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping
    public CaravanaDTO crearCaravana(@RequestBody CaravanaDTO caravanaDTO) {
        logger.info("Crear caravana");
        return caravanaService.crearCaravana(caravanaDTO);
    }

    @GetMapping("{idCaravana}")
    public CaravanaDTO buscarCaravana(@PathVariable("idCaravana") Long id) {
        logger.info("Vista de caravana");
        return caravanaService.buscarCaravana(id).orElseThrow();
    }

    @PutMapping
    public CaravanaDTO actualizarCaravana(@RequestBody CaravanaDTO caravanaDTO) {
        logger.info("Actualizar caravana");
        return caravanaService.actualizarCaravana(caravanaDTO);
    }

    @DeleteMapping("{idCaravana}")
    public void eliminarCaravana(@PathVariable("idCaravana") Long id) {
        logger.info("Eliminar caravana");
        caravanaService.eliminarCaravana(id);
    }

    @GetMapping("{idCaravana}/jugadores")
    public List<JugadorDTO> listarJugadores(@PathVariable("idCaravana") Long id) {
        logger.info("Lista de jugadores");
        return caravanaService.listarJugadores(id).orElseThrow();
    }

    @GetMapping("{idCaravana}/ciudad")
    public CiudadDTO recuperarCiudadCaravana(@PathVariable("idCaravana") Long id) {
        logger.info("Vista de ciudad de caravana");
        return caravanaService.recuperarCiudadCaravana(id).orElseThrow();
    }

    @GetMapping("{idCaravana}/estado-ciudad")
    public ResponseEntity<Map<String, Object>> obtenerEstadoCiudad(@PathVariable("idCaravana") Long idCaravana) {
        logger.info("Obteniendo estado de ciudad para caravana " + idCaravana);
        return caravanaRepository.findById(idCaravana).map(caravana -> {
            Map<String, Object> estado = new HashMap<>();
            estado.put("salud", caravana.getPuntosVida());
            estado.put("dinero", caravana.getDinero());
            estado.put("velocidad", caravana.getVelocidad());

            if (caravana.getCiudad() != null) {
                estado.put("ciudadNombre", caravana.getCiudad().getNombre());
            } else {
                estado.put("ciudadNombre", "Desconocida");
            }

            // Accedemos a tiempo del juego desde el primer jugador de la caravana
            if (caravana.getJugadores() != null && !caravana.getJugadores().isEmpty()) {
                Jugador jugador = caravana.getJugadores().get(0);
                Juego juego = jugador.getJuego();
                if (juego != null) {
                    estado.put("tiempoMaximo", juego.getTiempoLimite());
                }
            }

            return ResponseEntity.ok(estado);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("{idCaravana}/inventario")
    public List<InventarioCaravanaDTO> obtenerInventarioCaravana(@PathVariable("idCaravana") Long idCaravana) {
        logger.info("Obteniendo inventario de caravana {}", idCaravana);
        return caravanaService.obtenerInventarioCaravana(idCaravana);
    }
}

