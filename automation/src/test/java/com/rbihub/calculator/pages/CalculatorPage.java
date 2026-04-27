package com.rbihub.calculator.pages;

import com.rbihub.calculator.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

public class CalculatorPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By display = By.id("display");
    private final By calculatorRoot = By.cssSelector(".calculator");

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TestConfig.EXPLICIT_WAIT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(calculatorRoot));
    }

    public CalculatorPage clickLabel(String label) {
        button(label).click();
        pauseBetweenActions();
        return this;
    }

    public CalculatorPage clickSequence(String... labels) {
        Arrays.stream(labels).forEach(this::clickLabel);
        return this;
    }

    public String displayValue() {
        return driver.findElement(display).getDomProperty("value");
    }

    public CalculatorPage clear() {
        return clickLabel("C");
    }

    public String evaluate(String... labels) {
        clickSequence(labels);
        return displayValue();
    }

    public CalculatorPage waitForDisplayUpdate() {
        wait.withTimeout(Duration.ofSeconds(1))
                .until(driver -> !displayValue().isEmpty());
        return this;
    }

    private WebElement button(String label) {
        String xpath = "//button[normalize-space()=\"" + label + "\"]";
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    private void pauseBetweenActions() {
        long delayMs = TestConfig.stepDelayMs();
        if (delayMs <= 0) {
            return;
        }
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
