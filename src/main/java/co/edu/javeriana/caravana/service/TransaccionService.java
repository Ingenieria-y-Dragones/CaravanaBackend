package co.edu.javeriana.caravana.service;

import co.edu.javeriana.caravana.dto.*;
import co.edu.javeriana.caravana.model.*;
import co.edu.javeriana.caravana.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransaccionService {

    private final CiudadRepository ciudadRepository;
    private final CaravanaRepository caravanaRepository;
    private final ProductoRepository productoRepository;
    private final ServicioRepository servicioRepository;
    private final InventarioCiudadRepository inventarioCiudadRepository;
    private final InventarioCaravanaRepository inventarioCaravanaRepository;

    @Autowired
    public TransaccionService(
            CiudadRepository ciudadRepository,
            CaravanaRepository caravanaRepository,
            ProductoRepository productoRepository,
            ServicioRepository servicioRepository,
            InventarioCiudadRepository inventarioCiudadRepository,
            InventarioCaravanaRepository inventarioCaravanaRepository) {
        this.ciudadRepository = ciudadRepository;
        this.caravanaRepository = caravanaRepository;
        this.productoRepository = productoRepository;
        this.servicioRepository = servicioRepository;
        this.inventarioCiudadRepository = inventarioCiudadRepository;
        this.inventarioCaravanaRepository = inventarioCaravanaRepository;
    }

    @Transactional
    public Optional<TransaccionResultadoDTO> procesarCompraProducto(TransaccionCompraDTO transaccion) {
        try {
            // 1. Obtener entidades
            Caravana caravana = caravanaRepository.findById(transaccion.getIdCaravana())
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
            
            Producto producto = productoRepository.findById(transaccion.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no existe"));
            
            InventarioCiudad inventarioCiudad = inventarioCiudadRepository
                .findByCiudadIdAndProductoId(transaccion.getIdCiudad(), transaccion.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no disponible en la ciudad"));

            // 2. Convertir cantidad a Long
            Long cantidad = transaccion.getCantidad().longValue();
            
            // 3. Verificar stock ciudad
            if (inventarioCiudad.getExistencias() < cantidad) {
                throw new RuntimeException("Stock insuficiente en la ciudad");
            }

            // 4. Calcular precio compra (FO / (1 + S))
            float precioUnitario = inventarioCiudad.getFactorOferta() / (1 + inventarioCiudad.getExistencias());
            float costoTotal = precioUnitario * cantidad;

            // 5. Verificar fondos
            if (caravana.getDinero() < costoTotal) {
                throw new RuntimeException("Fondos insuficientes");
            }

            // 6. Actualizar inventario ciudad
            inventarioCiudad.setExistencias(inventarioCiudad.getExistencias() - cantidad);
            inventarioCiudadRepository.save(inventarioCiudad);

            // 7. Actualizar inventario caravana
            InventarioCaravana inventarioCaravana = inventarioCaravanaRepository
                .findByCaravanaIdAndProductoId(caravana.getId(), producto.getId())
                .orElse(new InventarioCaravana());

            if (inventarioCaravana.getId() == null) {
                inventarioCaravana.setCaravana(caravana);
                inventarioCaravana.setProducto(producto);
                inventarioCaravana.setExistencias(cantidad);
            } else {
                inventarioCaravana.setExistencias(inventarioCaravana.getExistencias() + cantidad);
            }
            inventarioCaravanaRepository.save(inventarioCaravana);

            // 8. Actualizar dinero caravana
            caravana.setDinero(caravana.getDinero() - costoTotal);
            caravanaRepository.save(caravana);

            return Optional.of(new TransaccionResultadoDTO(
                true,
                "Compra exitosa de " + cantidad + " " + producto.getNombre(),
                caravana.getDinero(),
                caravana.getPuntosVida()
            ));

        } catch (RuntimeException e) {
            return Optional.of(new TransaccionResultadoDTO(
                false,
                "Error en compra: " + e.getMessage(),
                0f,
                0
            ));
        }
    }

    @Transactional
    public Optional<TransaccionResultadoDTO> procesarVentaProducto(TransaccionVentaDTO transaccion) {
        try {
            // 1. Obtener entidades
            Caravana caravana = caravanaRepository.findById(transaccion.getIdCaravana())
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
            
            Producto producto = productoRepository.findById(transaccion.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no existe"));
            
            InventarioCaravana inventarioCaravana = inventarioCaravanaRepository
                .findByCaravanaIdAndProductoId(transaccion.getIdCaravana(), transaccion.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no en inventario"));

            InventarioCiudad inventarioCiudad = inventarioCiudadRepository
                .findByCiudadIdAndProductoId(transaccion.getIdCiudad(), transaccion.getIdProducto())
                .orElse(new InventarioCiudad());

            // 2. Convertir cantidad a Long
            Long cantidad = transaccion.getCantidad().longValue();
            
            // 3. Verificar stock caravana
            if (inventarioCaravana.getExistencias() < cantidad) {
                throw new RuntimeException("Stock insuficiente en la caravana");
            }

            // 4. Calcular precio venta (FD / (1 + S))
            float precioUnitario = inventarioCiudad.getFactorDemanda() / (1 + inventarioCiudad.getExistencias());
            float ingresoTotal = precioUnitario * cantidad;

            // 5. Actualizar inventario caravana
            inventarioCaravana.setExistencias(inventarioCaravana.getExistencias() - cantidad);
            if (inventarioCaravana.getExistencias() <= 0) {
                inventarioCaravanaRepository.delete(inventarioCaravana);
            } else {
                inventarioCaravanaRepository.save(inventarioCaravana);
            }

            // 6. Actualizar inventario ciudad
            if (inventarioCiudad.getId() == null) {
                Ciudad ciudad = ciudadRepository.findById(transaccion.getIdCiudad())
                    .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
                
                inventarioCiudad.setCiudad(ciudad);
                inventarioCiudad.setProducto(producto);
                inventarioCiudad.setExistencias(cantidad);
                inventarioCiudad.setFactorDemanda(1.0f);
                inventarioCiudad.setFactorOferta(1.0f);
            } else {
                inventarioCiudad.setExistencias(inventarioCiudad.getExistencias() + cantidad);
            }
            inventarioCiudadRepository.save(inventarioCiudad);

            // 7. Actualizar dinero caravana
            caravana.setDinero(caravana.getDinero() + ingresoTotal);
            caravanaRepository.save(caravana);

            return Optional.of(new TransaccionResultadoDTO(
                true,
                "Venta exitosa de " + cantidad + " " + producto.getNombre(),
                caravana.getDinero(),
                caravana.getPuntosVida()
            ));

        } catch (RuntimeException e) {
            return Optional.of(new TransaccionResultadoDTO(
                false,
                "Error en venta: " + e.getMessage(),
                0f,
                0
            ));
        }
    }

    @Transactional
    public Optional<TransaccionResultadoDTO> procesarCompraServicio(TransaccionCompraDTO transaccion) {
        try {
            Caravana caravana = caravanaRepository.findById(transaccion.getIdCaravana())
                .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
            
            Servicio servicio = servicioRepository.findById(transaccion.getIdServicio())
                .orElseThrow(() -> new RuntimeException("Servicio no disponible"));

            // Verificar fondos
            if (caravana.getDinero() < servicio.getPrecio()) {
                throw new RuntimeException("Fondos insuficientes");
            }

            // Aplicar servicio
            switch(servicio.getTipo()) {
                case REPARAR:
                    caravana.setPuntosVida(100);
                    break;
                case MEJORAR_CAPACIDAD:
                    caravana.setCapacidadMaximaCarga(caravana.getCapacidadMaximaCarga() * 1.5f);
                    break;
                case MEJORAR_VELOCIDAD:
                    caravana.setVelocidad(caravana.getVelocidad() * 1.2f);
                    break;
                case GUARDIAS:
                    caravana.setTieneGuardias(true);
                    break;
                default:
                    throw new RuntimeException("Servicio no reconocido: " + servicio.getTipo());
            }
            

            // Actualizar dinero
            caravana.setDinero(caravana.getDinero() - servicio.getPrecio());
            caravanaRepository.save(caravana);

            return Optional.of(new TransaccionResultadoDTO(
                true,
                "Servicio aplicado: " + servicio.getNombre(),
                caravana.getDinero(),
                caravana.getPuntosVida()
            ));

        } catch (RuntimeException e) {
            return Optional.of(new TransaccionResultadoDTO(
                false,
                "Error en servicio: " + e.getMessage(),
                0f,
                0
            ));
        }
    }
}
