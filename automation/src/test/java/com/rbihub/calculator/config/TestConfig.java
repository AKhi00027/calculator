package com.rbihub.calculator.config;

import java.time.Duration;

public final class TestConfig {
    private TestConfig() {
    }

    public static final Duration IMPLICIT_WAIT = Duration.ofSeconds(2);
    public static final Duration EXPLICIT_WAIT = Duration.ofSeconds(5);

    public static String baseUrl() {
        return System.getProperty(
                "baseUrl",
                "https://rbihubcodechallenge.github.io/calculator/index.html"
        );
    }

    public static String browser() {
        return System.getProperty("browser", "chrome").toLowerCase();
    }

    public static boolean headless() {
        return Boolean.parseBoolean(System.getProperty("headless", "true"));
    }

    public static long stepDelayMs() {
        String configuredDelay = System.getProperty("stepDelayMs");
        if (configuredDelay != null && !configuredDelay.isBlank()) {
            return Long.parseLong(configuredDelay);
        }
        // Default slower interaction speed for headed runs so execution is visible.
        return headless() ? 0L : 450L;
    }
}
