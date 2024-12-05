import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Test;

public class FunctionalTests {

    @Test
    void testEndToEndScenario() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to registration page
            driver.get("http://localhost:8080/register");
            driver.findElement(By.name("email")).sendKeys("test@example.com");
            driver.findElement(By.name("password")).sendKeys("password");
            driver.findElement(By.id("registerButton")).click();

            // Log in
            driver.get("http://localhost:8080/login");
            driver.findElement(By.name("email")).sendKeys("test@example.com");
            driver.findElement(By.name("password")).sendKeys("password");
            driver.findElement(By.id("loginButton")).click();

            // Add item to cart
            driver.get("http://localhost:8080/cart");
            driver.findElement(By.id("addItemButton")).click();

            // Place order
            driver.get("http://localhost:8080/orders");
            driver.findElement(By.id("placeOrderButton")).click();

            // Assert success
            WebElement successMessage = driver.findElement(By.id("orderSuccessMessage"));
            assertNotNull(successMessage);
        } finally {
            driver.quit();
        }
    }
}
