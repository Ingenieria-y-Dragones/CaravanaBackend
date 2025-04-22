package co.edu.javeriana.caravana.service;

import co.edu.javeriana.caravana.dto.RutaDTO;
import co.edu.javeriana.caravana.dto.RutaDestinoDTO;
import co.edu.javeriana.caravana.mapper.CiudadMapper;
import co.edu.javeriana.caravana.mapper.RutaMapper;
import co.edu.javeriana.caravana.model.Ruta;
import co.edu.javeriana.caravana.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    public RutaDTO crearRuta(RutaDTO rutaDTO) {
        rutaDTO.setId(null);
        Ruta ruta = RutaMapper.toEntity(rutaDTO);
        return RutaMapper.toDTO(rutaRepository.save(ruta));
    }

    public Optional<RutaDTO> buscarRuta(Long id) {
        return rutaRepository.findById(id).map(RutaMapper::toDTO);
    }

    public RutaDTO actualizarRuta(RutaDTO rutaDTO) {
        //TODO checkear que el id sea null
        Ruta ruta = RutaMapper.toEntity(rutaDTO);
        return RutaMapper.toDTO(rutaRepository.save(ruta));
    }

    public void eliminarRuta(Long id) {
        rutaRepository.deleteById(id);
    }

    public List<RutaDTO> listarRutas() {
        return rutaRepository.findAll().stream().map(RutaMapper::toDTO).toList();
    }

    public Optional<List<RutaDTO>> obtenerRutasDesdeCiudadDeCaravana(Long caravanaId) {
        List<Ruta> rutas = rutaRepository.findRutasByCiudadOrigenDeCaravana(caravanaId);
        if (rutas.isEmpty()) {
            return Optional.empty();
        }

        List<RutaDTO> rutasDTO = rutas.stream()
                .map(RutaMapper::toDTO)
                .toList();

        return Optional.of(rutasDTO);
    }

    public Optional<List<RutaDestinoDTO>> recuperarRutasDesdeCiudadDeCaravana(Long caravanaId) {
        List<Ruta> rutas = rutaRepository.findRutasByCiudadOrigenDeCaravana(caravanaId);
        if (rutas.isEmpty()) {
            return Optional.empty();
        }

        List<RutaDestinoDTO> rutasConDestinoDTO = rutas.stream()
                .map(ruta -> new RutaDestinoDTO(
                        RutaMapper.toDTO(ruta),
                        CiudadMapper.toDTO(ruta.getCiudadDestino())
                ))
                .toList();

        return Optional.of(rutasConDestinoDTO);
    }
}

