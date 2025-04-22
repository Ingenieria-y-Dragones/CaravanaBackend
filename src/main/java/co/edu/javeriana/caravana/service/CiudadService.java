package co.edu.javeriana.caravana.service;

import co.edu.javeriana.caravana.dto.CiudadDTO;
import co.edu.javeriana.caravana.dto.InventarioCiudadDTO;
import co.edu.javeriana.caravana.dto.ServicioOfrecidoDTO;
import co.edu.javeriana.caravana.model.Ciudad;
import co.edu.javeriana.caravana.model.InventarioCiudad;
import co.edu.javeriana.caravana.model.ServicioOfrecido;
import co.edu.javeriana.caravana.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CiudadService {

    private final CiudadRepository ciudadRepository;

    @Autowired
    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public Optional<CiudadDTO> obtenerCiudad(Long id) {
        return ciudadRepository.findById(id)
                .map(ciudad -> {
                    CiudadDTO dto = new CiudadDTO();
                    dto.setId(ciudad.getId());
                    dto.setNombre(ciudad.getNombre());
                    return dto;
                });
    }

    public List<InventarioCiudadDTO> obtenerProductosCiudad(Long idCiudad) {
        Ciudad ciudad = ciudadRepository.findById(idCiudad)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        return ciudad.getProductos().stream()
                .map(this::convertToInventarioCiudadDTO)
                .collect(Collectors.toList());
    }

    public List<ServicioOfrecidoDTO> obtenerServiciosCiudad(Long idCiudad) {
        Ciudad ciudad = ciudadRepository.findById(idCiudad)
                .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));

        return ciudad.getServicios().stream()
                .map(this::convertToServicioOfrecidoDTO)
                .collect(Collectors.toList());
    }

    private InventarioCiudadDTO convertToInventarioCiudadDTO(InventarioCiudad inventario) {
        InventarioCiudadDTO dto = new InventarioCiudadDTO();
        dto.setId(inventario.getId());
        dto.setExistencias(inventario.getExistencias());
        dto.setFactorDemanda(inventario.getFactorDemanda());
        dto.setFactorOferta(inventario.getFactorOferta());

        // Asegúrate de que Producto tenga un campo nombre
        dto.setNombreProducto(inventario.getProducto().getNombre());
        return dto;
    }

    private ServicioOfrecidoDTO convertToServicioOfrecidoDTO(ServicioOfrecido servicio) {
        ServicioOfrecidoDTO dto = new ServicioOfrecidoDTO();
        dto.setId(servicio.getId());
        dto.setPrecio(servicio.getPrecio());

        // Asegúrate de que Servicio tenga un campo nombre
        dto.setNombreServicio(servicio.getServicio().getNombre());
        return dto;
    }
}
