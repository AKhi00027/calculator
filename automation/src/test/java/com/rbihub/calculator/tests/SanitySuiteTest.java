package com.rbihub.calculator.tests;

import com.rbihub.calculator.core.BaseTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SanitySuiteTest extends BaseTest {

    @Test(groups = {"sanity", "smoke"})
    public void shouldLoadCalculatorAndEnterDigits() {
        calculator.clickSequence("1", "2", "0");

        assertThat(calculator.displayValue()).isEqualTo("120");
    }

    @Test(groups = {"sanity", "smoke"})
    public void shouldClearDisplay() {
        calculator.clickSequence("9", "+", "1");
        calculator.clear();

        assertThat(calculator.displayValue()).isEmpty();
    }

    @Test(groups = {"sanity"})
    public void shouldAddTwoNumbers() {
        String result = calculator.evaluate("2", "+", "4", "=");

        assertThat(result).isEqualTo("6");
    }

    @Test(groups = {"sanity"})
    public void shouldMultiplyTwoNumbers() {
        String result = calculator.evaluate("4", "×", "5", "=");

        assertThat(result).isEqualTo("20");
    }
}
