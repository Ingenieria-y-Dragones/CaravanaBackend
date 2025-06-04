package co.edu.javeriana.caravana;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import co.edu.javeriana.caravana.model.Caravana;
import co.edu.javeriana.caravana.model.Producto;
import co.edu.javeriana.caravana.model.TipoProducto;
import co.edu.javeriana.caravana.repository.CaravanaRepository;
import co.edu.javeriana.caravana.repository.ProductoRepository;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("system-testing")
public class TransaccionSystemTest {

    private static final float SALDO_INICIAL = 10000f;
    private String SERVER_URL = "http://localhost:4200";

    @Autowired
    private CaravanaRepository caravanaRepository;
    @Autowired
    private ProductoRepository productoRepository;

    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;

    @BeforeEach
    void init() {
        // Crea la caravana y el producto para la prueba
        caravanaRepository.save(new Caravana(
                "Caravana Test", 10f, 100f, SALDO_INICIAL, 100, true, 0f
        ));
        productoRepository.save(new Producto(TipoProducto.ESPECIAS));
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @AfterEach
    void end() {
        browser.close();
        playwright.close();
    }

    @Test
    void flujoTransaccionCompleta() {
        // 1. Navega a la página de login
        page.navigate(SERVER_URL + "/login");

        // 2. Llena el formulario de login (ajusta los datos según un usuario válido en tu BD)
        page.waitForSelector("#txtEmail");
        page.locator("#txtEmail").fill("ahmed@gmail.com");
        page.locator("#txtPassword").fill("ahmed123");
        page.locator("button[type='submit']").click();

        // 3. Espera a que la app redireccione y cargue la pantalla principal (ajusta el selector)
        page.waitForSelector(".panels-container");

        // 4. Navega a la página de ciudad/vista/1
        page.navigate(SERVER_URL + "/ciudad/vista/1");

        // 5. Espera a que aparezcan los productos para comprar
        page.waitForSelector(".btn-comprar");

        // 6. Encuentra todos los botones de compra en la página (uno por producto)
        List<Locator> botonesComprar = page.locator(".btn-comprar").all();

        // 7. Simula comprar el primer producto tres veces
        int compras = 3;
        for (int i = 0; i < compras; i++) {
            botonesComprar.get(0).click();
            page.waitForTimeout(300); // espera breve para simular usuario y evitar race conditions
        }

        // 8. Verifica el saldo actualizado (ajusta el cálculo según tu lógica de negocio)
        // Si tienes un resumen con el saldo, ajusta el selector y el cálculo
        // page.locator("#linkResumen").click(); // si tienes un botón para ver el resumen
        // Locator liCaravanaSaldo = page.locator("#liCaravanaSaldo");
        // liCaravanaSaldo.waitFor();
        // String saldoEsperado = "Saldo: " + (SALDO_INICIAL - compras * 100) + ".00";
        // String saldoReal = liCaravanaSaldo.textContent().trim();
        // assertEquals(saldoEsperado, saldoReal);

        // 9. Verifica que las transacciones aparecen en el historial
        // page.locator("#linkHistorial").click(); // si tienes un botón para ver el historial
        // Locator filasTransacciones = page.locator("//table[@id='tablaTransacciones']/tbody/tr/td[3]");
        // for (int i = 0; i < 50; i++) {
        //     if (filasTransacciones.count() == compras) break;
        //     page.waitForTimeout(100);
        // }
        // List<String> cantidadesReales = filasTransacciones.all().stream()
        //         .map(Locator::textContent)
        //         .map(String::trim)
        //         .collect(Collectors.toList());
        // List<String> cantidadesEsperadas = IntStream.range(0, compras)
        //         .mapToObj(i -> "1")
        //         .collect(Collectors.toList());
        // assertEquals(cantidadesEsperadas, cantidadesReales);

        // NOTA: Descomenta y ajusta la verificación de saldo e historial según los selectores reales de tu app.
    }
}