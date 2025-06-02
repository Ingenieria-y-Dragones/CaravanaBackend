package co.edu.javeriana.caravana.service;

import co.edu.javeriana.caravana.dto.JwtAuthenticationResponse;
import co.edu.javeriana.caravana.dto.LoginDTO;
import co.edu.javeriana.caravana.dto.UserRegistrationDTO;
import co.edu.javeriana.caravana.model.Jugador;
import co.edu.javeriana.caravana.model.Rol;
import co.edu.javeriana.caravana.model.TipoJugador;
import co.edu.javeriana.caravana.repository.JugadorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JugadorRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signup(UserRegistrationDTO request) {
        Jugador user = new Jugador(
                request.getNombre(),
                TipoJugador.CARAVANERO,
                request.getCorreo(),
                passwordEncoder.encode(request.getContrasenia()),
                Rol.CARAVANERO);

        userRepository.save(user);
        String jwt = jwtService.generateToken(user.getUsername());
        return new JwtAuthenticationResponse(jwt, user.getEmail(), user.getRol().name());
    }

    public JwtAuthenticationResponse login(LoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getContrasenia()));
        Jugador user = userRepository.findByEmail(request.getCorreo())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String jwt = jwtService.generateToken(user.getUsername());
        return new JwtAuthenticationResponse(jwt, user.getEmail(), user.getRol().name());
    }

}
