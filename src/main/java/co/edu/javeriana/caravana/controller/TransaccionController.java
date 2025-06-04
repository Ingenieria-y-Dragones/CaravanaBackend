package co.edu.javeriana.caravana.controller;

import co.edu.javeriana.caravana.dto.TransaccionCompraDTO;
import co.edu.javeriana.caravana.dto.TransaccionResultadoDTO;
import co.edu.javeriana.caravana.dto.TransaccionVentaDTO;
import co.edu.javeriana.caravana.model.Rol;
import co.edu.javeriana.caravana.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaccion")
@CrossOrigin(origins = "http://localhost:4200")
public class TransaccionController {

    private final TransaccionService transaccionService;

    @Autowired
    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @Secured({Rol.Codigo.CARAVANERO, Rol.Codigo.COMERCIANTE})
    @PostMapping("/compra/producto")
    public ResponseEntity<TransaccionResultadoDTO> comprarProducto(@RequestBody TransaccionCompraDTO transaccion) {
        return transaccionService.procesarCompraProducto(transaccion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @Secured({Rol.Codigo.CARAVANERO})
    @PostMapping("/compra/servicio")
    public ResponseEntity<TransaccionResultadoDTO> comprarServicio(@RequestBody TransaccionCompraDTO transaccion) {
        return transaccionService.procesarCompraServicio(transaccion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @Secured({Rol.Codigo.CARAVANERO, Rol.Codigo.COMERCIANTE})
    @PostMapping("/venta/producto")
    public ResponseEntity<TransaccionResultadoDTO> venderProducto(@RequestBody TransaccionVentaDTO transaccion) {
        return transaccionService.procesarVentaProducto(transaccion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}

