package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ParameterTest {
    WebDriver driver;
    @BeforeClass
    @Parameters("browser")
    private void setUp(String browser) {
        if(browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }else{
            throw new UnsupportedOperationException("Browser not supported");
        }
        driver.manage().window().maximize();
    }

    @Test
    @Parameters({"url", "expectedTitle"})
    private void testPageTitle(String url, String expectedTitle) {
        driver.get(url);
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    @Parameters({"username", "password"})
    private void testLogin(String student, String password) {
        driver.get("https://practicetestautomation.com/practice-test-login/");
        WebElement userField = driver.findElement(By.id("username"));
        userField.sendKeys(student);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        boolean success = driver.getPageSource().contains("Logged in successfully");
        Assert.assertTrue(success);
    }

    @AfterTest
    private void tearDown() {
        driver.quit();
    }
}
