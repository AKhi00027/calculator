package com.rbihub.calculator.core;

import com.rbihub.calculator.config.TestConfig;
import com.rbihub.calculator.pages.CalculatorPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public abstract class BaseTest {
    protected CalculatorPage calculator;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.createDriver();
        driver().get(TestConfig.baseUrl());
        calculator = new CalculatorPage(driver());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (!result.isSuccess()) {
            takeFailureScreenshot(result.getName());
        }
        DriverFactory.quitDriver();
        calculator = null;
    }

    protected WebDriver driver() {
        return DriverFactory.getDriver();
    }

    protected double displayAsDouble() {
        return Double.parseDouble(calculator.displayValue());
    }

    private void takeFailureScreenshot(String testName) {
        WebDriver activeDriver;
        try {
            activeDriver = driver();
        } catch (IllegalStateException ignored) {
            return;
        }

        if (!(activeDriver instanceof TakesScreenshot screenshotDriver)) {
            return;
        }

        File source = screenshotDriver.getScreenshotAs(OutputType.FILE);
        Path targetPath = Path.of("target", "screenshots", sanitizeFileName(testName) + ".png");
        try {
            Files.createDirectories(targetPath.getParent());
            Files.copy(source.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ignored) {
            // Do not block teardown if screenshot persistence fails.
        }
    }

    private String sanitizeFileName(String input) {
        return input.replaceAll("[^A-Za-z0-9._-]", "_");
    }
}
