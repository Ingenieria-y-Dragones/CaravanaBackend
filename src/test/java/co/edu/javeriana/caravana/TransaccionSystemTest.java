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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
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
        // 1. Login
        page.navigate(SERVER_URL + "/login");
        page.waitForSelector("#txtEmail");
        page.locator("#txtEmail").fill("ahmed@gmail.com");
        page.locator("#txtPassword").fill("ahmed123");
        page.locator("button[type='submit']").click();

        // 2. Espera a que cargue la página de caravana/vista/1
        page.waitForSelector(".dashboard-container");
        page.navigate(SERVER_URL + "/caravana/vista/1");
        page.waitForSelector(".dashboard-container");

        // 3. Haz clic en el botón "Comerciar" para ir a ciudad/vista/1
        page.locator("button.header-button:has-text('Comerciar')").click();

        // 4. Espera a que cargue la página de ciudad/vista/1
        page.waitForSelector(".panels-container");

        // 5. Compra productos
        List<Locator> botonesComprar = page.locator(".btn-comprar").all();
        int compras = 3;
        for (int i = 0; i < compras; i++) {
            botonesComprar.get(0).click();
            page.waitForTimeout(300);
        }
    }
}
