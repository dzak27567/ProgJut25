package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProduct_isSuccessful(ChromeDriver driver) throws Exception {
        // Navigate to product list page
        driver.get(baseUrl + "/product/list");

        // Click Create Product button
        List<WebElement> elements = driver.findElements(By.tagName("a"));
        WebElement createButton = elements.stream()
                .filter(e -> e.getText().equals("Create Product"))
                .findFirst()
                .orElseThrow();
        createButton.click();

        // Fill the form
        driver.findElement(By.id("nameInput")).sendKeys("Sabun Mandi Biru");
        driver.findElement(By.id("quantityInput")).sendKeys("50");
        driver.findElement(By.tagName("button")).click();

        // Verify product in the list
        List<WebElement> tableContent = driver.findElements(By.xpath("//table/tbody/tr/td"));
        assertEquals("Sabun Mandi Biru", tableContent.getFirst().getText());
        assertEquals("50", tableContent.get(1).getText());
    }


    @Test
    void createProduct_multipleProducts(ChromeDriver driver) throws Exception {
        // Create first product
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("nameInput")).sendKeys("Product Satu");
        driver.findElement(By.id("quantityInput")).sendKeys("25");
        driver.findElement(By.tagName("button")).click();

        // Create second product
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("nameInput")).sendKeys("Product Dua");
        driver.findElement(By.id("quantityInput")).sendKeys("75");
        driver.findElement(By.tagName("button")).click();

        // Verify both products
        List<WebElement> tableContent = driver.findElements(By.xpath("//table/tbody/tr/td"));
        assertTrue(tableContent.stream().anyMatch(e -> e.getText().equals("Product Satu")));
        assertTrue(tableContent.stream().anyMatch(e -> e.getText().equals("25")));
        assertTrue(tableContent.stream().anyMatch(e -> e.getText().equals("Product Dua")));
        assertTrue(tableContent.stream().anyMatch(e -> e.getText().equals("75")));
    }
}