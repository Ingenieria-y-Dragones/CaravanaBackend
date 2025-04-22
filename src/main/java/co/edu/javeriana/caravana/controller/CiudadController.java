package co.edu.javeriana.caravana.controller;

import co.edu.javeriana.caravana.dto.CiudadDTO;
import co.edu.javeriana.caravana.dto.InventarioCaravanaDTO;
import co.edu.javeriana.caravana.dto.InventarioCiudadDTO;
import co.edu.javeriana.caravana.dto.ServicioOfrecidoDTO;
import co.edu.javeriana.caravana.service.CaravanaService;
import co.edu.javeriana.caravana.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ciudad")
@CrossOrigin(origins = "http://localhost:4200")
public class CiudadController {

    private final CiudadService ciudadService;
    private final CaravanaService caravanaService;

    @Autowired
    public CiudadController(
            CiudadService ciudadService,
            CaravanaService caravanaService // Inyectar CaravanaService
    ) {
        this.ciudadService = ciudadService;
        this.caravanaService = caravanaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiudadDTO> obtenerCiudad(@PathVariable Long id) {
        return ciudadService.obtenerCiudad(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/productos")
    public List<InventarioCiudadDTO> obtenerProductosCiudad(@PathVariable Long id) {
        return ciudadService.obtenerProductosCiudad(id);
    }

    @GetMapping("/{id}/servicios")
    public List<ServicioOfrecidoDTO> obtenerServiciosCiudad(@PathVariable Long id) {
        return ciudadService.obtenerServiciosCiudad(id);
    }

    @GetMapping("/{id}/inventario")
    public List<InventarioCaravanaDTO> obtenerInventarioCaravana(@PathVariable Long id) {
        return caravanaService.obtenerInventarioCaravana(id); // Llamada al método de instancia
    }
}

