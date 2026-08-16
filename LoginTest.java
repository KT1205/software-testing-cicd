package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
    }

    @Test
    public void testApplication() {

        driver.get("http://localhost:8080");

        String title = driver.findElement(By.id("title"))
                .getText();

        Assertions.assertEquals(
                "Software Testing CI/CD Demo",
                title
        );

        driver.findElement(By.id("testButton"))
                .click();

        String result = driver.findElement(By.id("result"))
                .getText();

        Assertions.assertEquals(
                "Selenium test executed successfully!",
                result
        );
    }

    @AfterAll
    public static void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}