package co.edu.javeriana.caravana.service;

import co.edu.javeriana.caravana.dto.CaravanaDTO;
import co.edu.javeriana.caravana.dto.JugadorDTO;
import co.edu.javeriana.caravana.dto.RutaDTO;
import co.edu.javeriana.caravana.mapper.CaravanaMapper;
import co.edu.javeriana.caravana.mapper.JugadorMapper;
import co.edu.javeriana.caravana.mapper.RutaMapper;
import co.edu.javeriana.caravana.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JugadorService {
    @Autowired
    private JugadorRepository jugadorRepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return jugadorRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    public Optional<JugadorDTO> buscarJugador(String email) {
        return jugadorRepository.findByEmail(email).map(JugadorMapper::toDTO);
    }

    public Optional<CaravanaDTO> buscarCaravana(String email) {
        return jugadorRepository.findCaravanaByEmail(email).map(CaravanaMapper::toDTO);
    }
}
