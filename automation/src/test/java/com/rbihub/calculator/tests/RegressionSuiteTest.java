package com.rbihub.calculator.tests;

import com.rbihub.calculator.core.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class RegressionSuiteTest extends BaseTest {
    private static final double EPSILON = 0.000001;

    @DataProvider(name = "trigFunctions")
    public Object[][] trigFunctions() {
        return new Object[][]{
                {"sin", Math.sin(1.0), "sin(1)"},
                {"cos", Math.cos(1.0), "cos(1)"},
                {"tan", Math.tan(1.0), "tan(1)"}
        };
    }

    @Test(groups = {"regression"})
    public void minusButtonShouldPerformSubtraction() {
        String result = calculator.evaluate("9", "−", "4", "=");

        assertThat(Double.parseDouble(result)).as("Expected 9 - 4").isEqualTo(5.0);
    }

    @Test(groups = {"regression"})
    public void threeButtonShouldInputThree() {
        calculator.clickLabel("3");

        assertThat(calculator.displayValue()).isEqualTo("3");
    }

    @Test(groups = {"regression"})
    public void divisionShouldRespectOperandOrder() {
        String result = calculator.evaluate("8", "÷", "2", "=");

        assertThat(Double.parseDouble(result)).as("Expected 8 / 2").isEqualTo(4.0);
    }

    @Test(groups = {"regression"})
    public void parenthesesShouldPreserveExpressionOrder() {
        String result = calculator.evaluate("(", "2", "+", "3", ")", "×", "4", "=");

        assertThat(Double.parseDouble(result)).as("Expected (2 + 3) * 4").isEqualTo(20.0);
    }

    @Test(dataProvider = "trigFunctions", groups = {"regression"})
    public void trigFunctionShouldComputeJavaMathEquivalent(String function, double expectedValue, String expressionLabel) {
        calculator.clickLabel("1");
        calculator.clickLabel(function);

        assertThat(displayAsDouble())
                .as("Expected %s in radians", expressionLabel)
                .isCloseTo(expectedValue, within(EPSILON));
    }

    @Test(groups = {"regression"})
    public void sqrtFunctionShouldComputeSquareRoot() {
        calculator.clickLabel("9");
        calculator.clickLabel("√");

        assertThat(displayAsDouble())
                .as("Expected sqrt(9)")
                .isCloseTo(3.0, within(EPSILON));
    }

    @Test(groups = {"regression"})
    public void logFunctionShouldComputeBaseTenLogarithm() {
        calculator.clickSequence("1", "0", "0");
        calculator.clickLabel("log");

        assertThat(displayAsDouble())
                .as("Expected log10(100)")
                .isCloseTo(2.0, within(EPSILON));
    }

    @Test(groups = {"regression"})
    public void pressingEqualsOnEmptyDisplayShouldNotShowUndefined() {
        calculator.clear();
        calculator.clickLabel("=");

        assertThat(calculator.displayValue()).isEmpty();
    }

    @Test(groups = {"regression"})
    public void scientificFunctionsShouldShowErrorForEmptyDisplay() {
        calculator.clear();
        calculator.clickLabel("sin");

        assertThat(calculator.displayValue()).isEqualTo("Error");
    }
}
