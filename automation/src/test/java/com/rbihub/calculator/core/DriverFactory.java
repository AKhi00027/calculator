package com.rbihub.calculator.core;

import com.rbihub.calculator.config.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static void createDriver() {
        WebDriver webDriver = switch (TestConfig.browser()) {
            case "firefox" -> new FirefoxDriver(firefoxOptions());
            case "edge" -> new EdgeDriver(edgeOptions());
            default -> new ChromeDriver(chromeOptions());
        };
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(TestConfig.IMPLICIT_WAIT);
        DRIVER.set(webDriver);
    }

    public static WebDriver getDriver() {
        WebDriver webDriver = DRIVER.get();
        if (webDriver == null) {
            throw new IllegalStateException("Driver has not been initialized for this thread");
        }
        return webDriver;
    }

    public static void quitDriver() {
        WebDriver webDriver = DRIVER.get();
        if (webDriver != null) {
            webDriver.quit();
            DRIVER.remove();
        }
    }

    private static ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (TestConfig.headless()) {
            options.addArguments("--headless=new");
        }
        options.addArguments(
                "--window-size=1920,1080",
                "--disable-gpu",
                "--no-sandbox",
                "--disable-dev-shm-usage"
        );
        return options;
    }

    private static FirefoxOptions firefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (TestConfig.headless()) {
            options.addArguments("-headless");
        }
        return options;
    }

    private static EdgeOptions edgeOptions() {
        EdgeOptions options = new EdgeOptions();
        if (TestConfig.headless()) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1920,1080");
        return options;
    }
}

