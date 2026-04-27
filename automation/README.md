# Scientific Calculator Automation Framework (Java + Selenium)

This repository contains a CI-ready Selenium UI automation framework for validating:

- Core calculator sanity checks
- Deeper regression scenarios
- Defect evidence with reproducible failures and screenshots

Target application:
`https://rbihubcodechallenge.github.io/calculator/index.html`

## Stack

- Java 21+
- Maven
- Selenium WebDriver 4
- TestNG
- AssertJ

## Project Structure

```text
src/test/java/com/rbihub/calculator
├── config      # Environment/runtime configuration
├── core        # Driver lifecycle + base test hooks
├── pages       # Page Object Model
└── tests       # Sanity and regression suites
```

Additional docs:

- [`docs/TEST_STRATEGY.md`](docs/TEST_STRATEGY.md)
- [`docs/BUG_REPORT.md`](docs/BUG_REPORT.md)

## How To Run

Run commands from the `automation/` directory.

Run complete suite:

```bash
mvn clean test -Dheadless=true -Dbrowser=chrome
```

Run in headed mode with visible step delay:

```bash
mvn clean test -Dheadless=false -Dbrowser=chrome -DstepDelayMs=700
```

Run sanity only:

```bash
mvn clean test -Psanity -Dheadless=true
```

Run regression only:

```bash
mvn clean test -Pregression -Dheadless=true
```

## Reporting

- TestNG XML/HTML outputs: `target/surefire-reports/`
- Failure screenshots: `target/screenshots/`

## CI

GitHub Actions workflow:
`/.github/workflows/ci.yml`

It runs tests headlessly on every push/PR and uploads reports/screenshots as artifacts.
