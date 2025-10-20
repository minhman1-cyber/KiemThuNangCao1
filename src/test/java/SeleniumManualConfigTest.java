import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumManualConfigTest {
    private WebDriver driver;
    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testDriver() {
        driver.get("https://www.google.com");
        Assert.assertEquals(driver.getTitle(), "Google");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
