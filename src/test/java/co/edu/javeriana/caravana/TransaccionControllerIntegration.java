package co.edu.javeriana.caravana;

import co.edu.javeriana.caravana.model.*;
import co.edu.javeriana.caravana.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Pruebas de integración para los endpoints de transacciones.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test") // Usa la configuración de seguridad de pruebas
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"}) // Simula usuario autenticado
public class TransaccionControllerIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CaravanaRepository caravanaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private CiudadRepository ciudadRepository;
    @Autowired
    private InventarioCaravanaRepository inventarioCaravanaRepository;
    @Autowired
    private InventarioCiudadRepository inventarioCiudadRepository;

    private Caravana caravana;
    private Producto producto;
    private Servicio servicio;
    private Ciudad ciudad;
    private String payloadActual;

    /**
     * Limpia todos los datos y crea entidades nuevas antes de cada prueba.
     */
    @BeforeEach
    void setup() {
        inventarioCaravanaRepository.deleteAll();
        inventarioCiudadRepository.deleteAll();
        caravanaRepository.deleteAll();
        productoRepository.deleteAll();
        servicioRepository.deleteAll();
        ciudadRepository.deleteAll();

        caravana = new Caravana();
        caravana.setNombre("Caravana Test");
        caravana.setDinero(1000f);
        caravana.setPuntosVida(100);
        caravanaRepository.save(caravana);

        producto = new Producto();
        producto.setNombre("Producto Test");
        productoRepository.save(producto);

        servicio = new Servicio();
        servicio.setNombre("Servicio Test");
        servicioRepository.save(servicio);

        ciudad = new Ciudad();
        ciudad.setNombre("Ciudad Test");
        ciudadRepository.save(ciudad);
    }

    @Test
    public void testVentaInventarioInsuficiente() throws Exception {
        prepararInventarioCaravana(1L);
        construirPayloadVenta(5);
        realizarVenta()
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.exito").value(false))
                .andExpect((ResultMatcher) jsonPath("$.mensaje").value("No tienes suficiente inventario"));
    }

    @Test
    public void testIdsInvalidos() throws Exception {
        mockMvc.perform(post("/transaccion/compra/producto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "caravanaId": 999,
                        "ciudadId": 999,
                        "productoId": 999,
                        "cantidad": 1
                    }"""))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testTransaccionRepetida() throws Exception {
        prepararInventarioCiudad(1L);
        caravana.setDinero(1000f);
        caravanaRepository.save(caravana);

        // Primera compra exitosa
        realizarCompra()
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.exito").value(true));

        // Segunda compra falla por inventario insuficiente
        realizarCompra()
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.exito").value(false))
                .andExpect((ResultMatcher) jsonPath("$.mensaje").value("Inventario insuficiente en ciudad"));
    }

    @Test
    public void testCamposInvalidos() throws Exception {
        // Test campo nulo
        mockMvc.perform(post("/transaccion/compra/producto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "caravanaId": null,
                        "ciudadId": 1,
                        "productoId": 1
                    }""")) // Falta cantidad
                .andExpect(status().isBadRequest());

        // Test tipo incorrecto
        mockMvc.perform(post("/transaccion/compra/producto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "caravanaId": "invalid_id",
                        "ciudadId": 1,
                        "productoId": 1,
                        "cantidad": 1
                    }"""))
                .andExpect(status().isBadRequest());
    }

    // Helpers para manipular inventarios

    /**
     * Agrega existencias de un producto en el inventario de la caravana.
     */
    private void prepararInventarioCaravana(Long existencias) {
        InventarioCaravana inv = new InventarioCaravana();
        inv.setCaravana(caravana);
        inv.setProducto(producto);
        inv.setExistencias(existencias);
        inventarioCaravanaRepository.save(inv);
    }

    /**
     * Agrega existencias de un producto en el inventario de la ciudad.
     */
    private void prepararInventarioCiudad(Long existencias) {
        InventarioCiudad inv = new InventarioCiudad();
        inv.setCiudad(ciudad);
        inv.setProducto(producto);
        inv.setExistencias(existencias);
        inv.setFactorDemanda(1f);
        inv.setFactorOferta(1f);
        inventarioCiudadRepository.save(inv);
    }

    /**
     * Construye el payload para simular una venta.
     */
    private void construirPayloadVenta(int cantidad) {
        payloadActual = String.format("""
            {
                "caravanaId": %d,
                "ciudadId": %d,
                "productoId": %d,
                "cantidad": %d
            }""",
                caravana.getId(), ciudad.getId(), producto.getId(), cantidad);
    }

    /**
     * Ejecuta una compra simulando una petición HTTP.
     */
    private ResultActions realizarCompra() throws Exception {
        String payload = String.format("""
            {
                "caravanaId": %d,
                "ciudadId": %d,
                "productoId": %d,
                "cantidad": 1
            }""",
                caravana.getId(), ciudad.getId(), producto.getId());
        return mockMvc.perform(post("/transaccion/compra/producto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload));
    }

    /**
     * Ejecuta una venta simulando una petición HTTP.
     */
    private ResultActions realizarVenta() throws Exception {
        return mockMvc.perform(post("/transaccion/venta/producto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadActual));
    }
}
