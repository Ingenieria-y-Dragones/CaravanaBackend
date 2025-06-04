package co.edu.javeriana.caravana.model;

import org.springframework.security.core.GrantedAuthority;

public enum Rol implements GrantedAuthority {
    CARAVANERO(Codigo.CARAVANERO),
    COMERCIANTE(Codigo.COMERCIANTE);

    private final String authority;

    Rol(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public class Codigo {
        public static final String CARAVANERO = "CARAVANERO";
        public static final String COMERCIANTE = "COMERCIANTE";
    }
}
