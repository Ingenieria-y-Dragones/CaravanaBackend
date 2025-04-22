package co.edu.javeriana.caravana.init;

import co.edu.javeriana.caravana.model.*;
import co.edu.javeriana.caravana.repository.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class DBInitializer implements CommandLineRunner {
    @Autowired
    private CaravanaRepository caravanaRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @Autowired
    private CompraServicioRepository compraServicioRepository;

    @Autowired
    private InventarioCaravanaRepository inventarioCaravanaRepository;

    @Autowired
    private InventarioCiudadRepository inventarioCiudadRepository;

    @Autowired
    private JuegoRepository juegoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private ServicioOfrecidoRepository servicioOfrecidoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        logger.info("Iniciando carga de datos iniciales...");

        // Inicializar juego
        crearJuego();

        // Inicializar productos
        crearProductos();

        // Inicializar servicios
        crearServicios();

        // Inicializar ciudades
        crearCiudades();

        // Inicializar rutas entre ciudades
        crearRutas();

        // Inicializar inventarios de ciudades
        crearInventariosCiudades();

        // Inicializar servicios ofrecidos en ciudades
        crearServiciosOfrecidos();

        // Inicializar caravanas
        crearCaravanas();

        // Inicializar jugadores
        crearJugadores();

        // Inicializar inventarios de caravanas
        crearInventariosCaravanas();

        // Inicializar compras de servicios
        crearComprasServicios();

        logger.info("Carga de datos iniciales completada con exito");
    }

    private void crearJuego() {
        if (juegoRepository.count() == 0) {
            Juego juego = new Juego(1.0F, 1000.0F); // Tiempo límite 1 hora, ganancias mínimas 1000
            juegoRepository.save(juego);
            logger.info("Juego creado: tiempo limite={}, ganancias minimas={}", juego.getTiempoLimite(), juego.getGananciasMinimas());
        }
    }

    private void crearProductos() {
        if (productoRepository.count() == 0) {
            List<Producto> productos = new ArrayList<>();

            for (TipoProducto tipo : TipoProducto.values()) {
                Producto producto = new Producto(tipo);
                producto.setNombre(tipo.name()); // Asignar nombre basado en el tipo
                productos.add(producto);
            }

            productoRepository.saveAll(productos);
            logger.info("Productos creados: {}", productos.size());
        }
    }


    private void crearServicios() {
        if (servicioRepository.count() == 0) {
            List<Servicio> servicios = new ArrayList<>();

            for (TipoServicio tipo : TipoServicio.values()) {
                Servicio servicio = new Servicio(tipo);
                servicio.setNombre(tipo.name()); // Asignar nombre basado en el tipo
                servicios.add(servicio);
            }

            servicioRepository.saveAll(servicios);
            logger.info("Servicios creados: {}", servicios.size());
        }
    }


    private void crearCiudades() {
        if (ciudadRepository.count() == 0) {
            List<Ciudad> ciudades = Arrays.asList(
                    new Ciudad("Valdruna", 0.05F),   // index 0
                    new Ciudad("Eliria", 0.06F),     // index 1
                    new Ciudad("Cairven", 0.07F),    // index 2
                    new Ciudad("Tarendor", 0.08F)    // index 3
            );

            ciudadRepository.saveAll(ciudades);
            logger.info("Ciudades creadas: {}", ciudades.size());
        }
    }

    private void crearRutas() {
        if (rutaRepository.count() == 0) {
            List<Ciudad> ciudades = ciudadRepository.findAll();
            List<Ruta> rutas = new ArrayList<>();

            // Valdruna (0) - Eliria (1)
            Ruta rutaValdrunaEliria = new Ruta("Senda de las Mil Cavernas", 180.0F, 10.0F, TipoPeligro.BANDIDOS);
            rutaValdrunaEliria.setCiudadOrigen(ciudades.get(0));
            rutaValdrunaEliria.setCiudadDestino(ciudades.get(1));
            rutas.add(rutaValdrunaEliria);

            Ruta rutaEliriaValdruna = new Ruta("Senda de las Mil Cavernas (vuelta)", 180.0F, 6.0F, TipoPeligro.BANDIDOS);
            rutaEliriaValdruna.setCiudadOrigen(ciudades.get(1));
            rutaEliriaValdruna.setCiudadDestino(ciudades.get(0));
            rutas.add(rutaEliriaValdruna);

            // Valdruna (0) - Cairven (2) - ruta 1
            Ruta rutaValdrunaCairven1 = new Ruta("Túnel de los Enanos", 220.0F, 8.0F, TipoPeligro.DESASTRES_NATURALES);
            rutaValdrunaCairven1.setCiudadOrigen(ciudades.get(0));
            rutaValdrunaCairven1.setCiudadDestino(ciudades.get(2));
            rutas.add(rutaValdrunaCairven1);

            Ruta rutaCairvenValdruna1 = new Ruta("Túnel de los Enanos (vuelta)", 220.0F, 8.0F, TipoPeligro.DESASTRES_NATURALES);
            rutaCairvenValdruna1.setCiudadOrigen(ciudades.get(2));
            rutaCairvenValdruna1.setCiudadDestino(ciudades.get(0));
            rutas.add(rutaCairvenValdruna1);

            // Valdruna (0) - Cairven (2) - ruta 2
            Ruta rutaValdrunaCairven2 = new Ruta("Camino de la Serpiente Helada", 240.0F, 12.0F, TipoPeligro.BANDIDOS);
            rutaValdrunaCairven2.setCiudadOrigen(ciudades.get(0));
            rutaValdrunaCairven2.setCiudadDestino(ciudades.get(2));
            rutas.add(rutaValdrunaCairven2);

            Ruta rutaCairvenValdruna2 = new Ruta("Camino de la Serpiente Helada (vuelta)", 240.0F, 7.0F, TipoPeligro.BANDIDOS);
            rutaCairvenValdruna2.setCiudadOrigen(ciudades.get(2));
            rutaCairvenValdruna2.setCiudadDestino(ciudades.get(0));
            rutas.add(rutaCairvenValdruna2);

            // Valdruna (0) - Tarendor (3)
            Ruta rutaValdrunaTarendor = new Ruta("Ruta del Dragón", 300.0F, 0.0F, TipoPeligro.NINGUNO);
            rutaValdrunaTarendor.setCiudadOrigen(ciudades.get(0));
            rutaValdrunaTarendor.setCiudadDestino(ciudades.get(3));
            rutas.add(rutaValdrunaTarendor);

            Ruta rutaTarendorValdruna = new Ruta("Ruta del Dragón (vuelta)", 300.0F, 0.0F, TipoPeligro.NINGUNO);
            rutaTarendorValdruna.setCiudadOrigen(ciudades.get(3));
            rutaTarendorValdruna.setCiudadDestino(ciudades.get(0));
            rutas.add(rutaTarendorValdruna);

            // Eliria (1) - Tarendor (3)
            Ruta rutaEliriaTarendor = new Ruta("Sendero de los Cielos", 200.0F, 5.0F, TipoPeligro.DESASTRES_NATURALES);
            rutaEliriaTarendor.setCiudadOrigen(ciudades.get(1));
            rutaEliriaTarendor.setCiudadDestino(ciudades.get(3));
            rutas.add(rutaEliriaTarendor);

            Ruta rutaTarendorEliria = new Ruta("Sendero de los Cielos (vuelta)", 200.0F, 4.0F, TipoPeligro.DESASTRES_NATURALES);
            rutaTarendorEliria.setCiudadOrigen(ciudades.get(3));
            rutaTarendorEliria.setCiudadDestino(ciudades.get(1));
            rutas.add(rutaTarendorEliria);

            // Cairven (2) - Tarendor (3)
            Ruta rutaCairvenTarendor = new Ruta("Paso del Filo Sombrío", 280.0F, 9.0F, TipoPeligro.BANDIDOS);
            rutaCairvenTarendor.setCiudadOrigen(ciudades.get(2));
            rutaCairvenTarendor.setCiudadDestino(ciudades.get(3));
            rutas.add(rutaCairvenTarendor);

            Ruta rutaTarendorCairven = new Ruta("Paso del Filo Sombrío (vuelta)", 280.0F, 6.0F, TipoPeligro.BANDIDOS);
            rutaTarendorCairven.setCiudadOrigen(ciudades.get(3));
            rutaTarendorCairven.setCiudadDestino(ciudades.get(2));
            rutas.add(rutaTarendorCairven);

            rutaRepository.saveAll(rutas);
            logger.info("Rutas creadas: {}", rutas.size());
        }
    }

    private void crearInventariosCiudades() {
        if (inventarioCiudadRepository.count() == 0) {
            List<Ciudad> ciudades = ciudadRepository.findAll();
            List<Producto> productos = productoRepository.findAll();
            List<InventarioCiudad> inventarios = new ArrayList<>();

            // Para cada ciudad y producto, crear un inventario con valores aleatorios pero coherentes
            for (Ciudad ciudad : ciudades) {
                for (Producto producto : productos) {
                    Float factorDemanda = 0.5F + new Random().nextFloat() * 1.5F; // Entre 0.5 y 2.0
                    Float factorOferta = 0.5F + new Random().nextFloat() * 1.5F; // Entre 0.5 y 2.0
                    Long existencias = 50L + Math.round(new Random().nextFloat() * 200); // Entre 50 y 250

                    InventarioCiudad inventario = new InventarioCiudad(existencias, factorDemanda, factorOferta);
                    inventario.setCiudad(ciudad);
                    inventario.setProducto(producto);
                    inventarios.add(inventario);
                }
            }

            inventarioCiudadRepository.saveAll(inventarios);
            logger.info("Inventarios de ciudades creados: {}", inventarios.size());
        }
    }

    private void crearServiciosOfrecidos() {
        if (servicioOfrecidoRepository.count() == 0) {
            List<Ciudad> ciudades = ciudadRepository.findAll();
            List<Servicio> servicios = servicioRepository.findAll();
            List<ServicioOfrecido> serviciosOfrecidos = new ArrayList<>();

            // Para cada ciudad, ofrecer algunos servicios con precios variados
            for (Ciudad ciudad : ciudades) {
                for (Servicio servicio : servicios) {
                    // Precio base según el tipo de servicio
                    float precioBase = switch (servicio.getTipo()) {
                        case REPARAR -> 50.0F;
                        case MEJORAR_CAPACIDAD -> 150.0F;
                        case MEJORAR_VELOCIDAD -> 200.0F;
                        case GUARDIAS -> 100.0F;
                        default -> 75.0F;
                    };

                    // Ajuste según el impuesto de la ciudad
                    float precio = precioBase * (1 + ciudad.getImpuesto());

                    ServicioOfrecido servicioOfrecido = new ServicioOfrecido(precio);
                    servicioOfrecido.setCiudad(ciudad);
                    servicioOfrecido.setServicio(servicio);
                    serviciosOfrecidos.add(servicioOfrecido);
                }
            }

            servicioOfrecidoRepository.saveAll(serviciosOfrecidos);
            logger.info("Servicios ofrecidos creados: {}", serviciosOfrecidos.size());
        }
    }

    private void crearCaravanas() {
        if (caravanaRepository.count() == 0) {
            List<Ciudad> ciudades = ciudadRepository.findAll();
            List<Caravana> caravanas = new ArrayList<>();

            if (!ciudades.isEmpty()) {
                // Caravana 1 en la primera ciudad
                Caravana caravana1 = new Caravana(
                        "Caravana del Desierto",
                        30.0F,  // velocidad
                        500.0F, // capacidad máxima de carga
                        1000.0F, // dinero inicial
                        100,    // puntos de vida
                        false,  // no tiene guardias inicialmente
                        0.0F    // tiempo transcurrido
                );
                caravana1.setCiudad(ciudades.get(0));
                caravanas.add(caravana1);

                // Caravana 2 en otra ciudad
                Caravana caravana2 = new Caravana(
                        "Mercaderes del Sur",
                        25.0F,  // velocidad
                        700.0F, // capacidad máxima de carga
                        1200.0F, // dinero inicial
                        100,    // puntos de vida
                        true,   // tiene guardias
                        0.0F    // tiempo transcurrido
                );

                int indiceSegundaCiudad = Math.min(1, ciudades.size() - 1);
                caravana2.setCiudad(ciudades.get(indiceSegundaCiudad));
                caravanas.add(caravana2);
            }

            caravanaRepository.saveAll(caravanas);
            logger.info("Caravanas creadas: {}", caravanas.size());
        }
    }

    private void crearJugadores() {
        if (jugadorRepository.count() == 0) {
            List<Caravana> caravanas = caravanaRepository.findAll();
            List<Jugador> jugadores = new ArrayList<>();

            if (!caravanas.isEmpty()) {
                // Jugadores para la primera caravana
                Jugador jugador1 = new Jugador("Ahmed", TipoJugador.CARAVANERO);
                jugador1.setCaravana(caravanas.get(0));
                jugadores.add(jugador1);

                Jugador jugador2 = new Jugador("Isabella", TipoJugador.COMERCIANTE);
                jugador2.setCaravana(caravanas.get(0));
                jugadores.add(jugador2);

                // Jugadores para la segunda caravana si existe
                if (caravanas.size() > 1) {
                    Jugador jugador3 = new Jugador("Carlos", TipoJugador.CARAVANERO);
                    jugador3.setCaravana(caravanas.get(1));
                    jugadores.add(jugador3);

                    Jugador jugador4 = new Jugador("Fatima", TipoJugador.COMERCIANTE);
                    jugador4.setCaravana(caravanas.get(1));
                    jugadores.add(jugador4);
                }
            }

            jugadorRepository.saveAll(jugadores);
            logger.info("Jugadores creados: {}", jugadores.size());
        }
    }

    private void crearInventariosCaravanas() {
        if (inventarioCaravanaRepository.count() == 0) {
            List<Caravana> caravanas = caravanaRepository.findAll();
            List<Producto> productos = productoRepository.findAll();
            List<InventarioCaravana> inventarios = new ArrayList<>();

            for (Caravana caravana : caravanas) {
                for (Producto producto : productos) {
                    // Asignamos existencias iniciales aleatorias pero razonables
                    Long existencias = Long.valueOf(Math.round(new Random().nextFloat() * 50)); // Entre 0 y 50

                    // Aseguramos que no exceda la capacidad máxima de la caravana
                    // (Asumiendo que cada unidad ocupa 1 unidad de espacio)
                    float capacidadUsada = existencias;
                    if (capacidadUsada <= caravana.getCapacidadMaximaCarga()) {
                        InventarioCaravana inventario = new InventarioCaravana(existencias);
                        inventario.setCaravana(caravana);
                        inventario.setProducto(producto);
                        inventarios.add(inventario);
                    }
                }
            }

            inventarioCaravanaRepository.saveAll(inventarios);
            logger.info("Inventarios de caravanas creados: {}", inventarios.size());
        }
    }

    private void crearComprasServicios() {
        if (compraServicioRepository.count() == 0) {
            List<Caravana> caravanas = caravanaRepository.findAll();
            List<Servicio> servicios = servicioRepository.findAll();
            List<Ciudad> ciudades = ciudadRepository.findAll();
            List<CompraServicio> compras = new ArrayList<>();

            // Solo creamos algunas compras de ejemplo
            if (!caravanas.isEmpty() && !servicios.isEmpty() && !ciudades.isEmpty()) {
                // La primera caravana compra un servicio en su ciudad actual
                CompraServicio compra1 = new CompraServicio();
                compra1.setCaravana(caravanas.get(0));
                compra1.setCiudad(caravanas.get(0).getCiudad());

                // Buscamos un servicio, por ejemplo GUARDIAS
                Servicio servicioGuardias = servicios.stream()
                        .filter(s -> s.getTipo() == TipoServicio.GUARDIAS)
                        .findFirst()
                        .orElse(servicios.get(0));

                compra1.setServicio(servicioGuardias);
                compras.add(compra1);

                // Si hay una segunda caravana, también compra un servicio
                if (caravanas.size() > 1) {
                    CompraServicio compra2 = new CompraServicio();
                    compra2.setCaravana(caravanas.get(1));
                    compra2.setCiudad(caravanas.get(1).getCiudad());

                    // Buscamos otro servicio, por ejemplo MEJORAR_VELOCIDAD
                    Servicio servicioVelocidad = servicios.stream()
                            .filter(s -> s.getTipo() == TipoServicio.MEJORAR_VELOCIDAD)
                            .findFirst()
                            .orElse(servicios.get(0));

                    compra2.setServicio(servicioVelocidad);
                    compras.add(compra2);
                }
            }

            compraServicioRepository.saveAll(compras);
            logger.info("Compras de servicios creadas: {}", compras.size());
        }
    }
}