# Scientific Calculator - QA Bug Report

## Test Execution Summary

- Application under test: `https://rbihubcodechallenge.github.io/calculator/index.html`
- Latest execution date: 27 Apr 2026, 10:00 IST
- Framework: Java + Selenium + TestNG + Allure
- Latest run command: `mvn clean test -Dheadless=true -Dbrowser=chrome`
- Result: `15` total, `9` passed, `6` failed
- Test report: `target/surefire-reports/index.html`
- Failure screenshots: `target/screenshots/`

## Execution History

| Date (IST) | Command | Mode | Result |
|---|---|---|---|
| 25 Apr 2026 12:31 | `mvn clean test -Dheadless=true -Dbrowser=chrome` | Headless full suite | 9 total, 3 passed, 6 failed |
| 25 Apr 2026 12:39 | `mvn clean test -Dheadless=true -Dbrowser=chrome` | Headless full suite | 15 total, 9 passed, 6 failed |
| 25 Apr 2026 12:43 | `mvn clean test -Dheadless=false -Dbrowser=chrome` | Headed full suite | 15 total, 9 passed, 6 failed |
| 25 Apr 2026 12:46 | `mvn test -Psanity -Dheadless=false -Dbrowser=chrome -DstepDelayMs=500` | Headed sanity (visual) | 4 total, 4 passed, 0 failed |
| 25 Apr 2026 12:49 | `mvn test -Psanity -Dheadless=true -Dbrowser=chrome` | Headless sanity | 4 total, 4 passed, 0 failed |
| 25 Apr 2026 12:54 | `mvn clean test -Dheadless=false -Dbrowser=chrome -DstepDelayMs=700` | Headed full suite (visual) | 15 total, 9 passed, 6 failed |
| 27 Apr 2026 10:00 | `mvn clean test -Dheadless=true -Dbrowser=chrome` | Headless full suite | 15 total, 9 passed, 6 failed |

## Defect List

### CALC-001 - `3` key enters `0` instead of `3`

- Severity: High
- Area: Numeric input mapping
- Steps to reproduce:
  1. Open calculator page.
  2. Click `3`.
- Expected: Display shows `3`.
- Actual: Display shows `0`.
- Impact: Core input is incorrect; many expressions become invalid.
- Evidence: `target/screenshots/threeButtonShouldInputThree.png`

### CALC-002 - Minus (`−`) operation performs division behavior

- Severity: Critical
- Area: Basic arithmetic
- Steps to reproduce:
  1. Open calculator page.
  2. Click `9`, `−`, `4`, `=`.
- Expected: Display shows `5`.
- Actual: Display shows `0.4444444444444444`.
- Impact: Subtraction is unusable; basic calculator function is broken.
- Evidence: `target/screenshots/minusButtonShouldPerformSubtraction.png`

### CALC-003 - Division uses reversed operand order

- Severity: Critical
- Area: Basic arithmetic
- Steps to reproduce:
  1. Open calculator page.
  2. Click `8`, `÷`, `2`, `=`.
- Expected: Display shows `4`.
- Actual: Display shows `0.25`.
- Impact: Division results are mathematically incorrect for users.
- Evidence: `target/screenshots/divisionShouldRespectOperandOrder.png`

### CALC-004 - Parentheses/precedence evaluation is incorrect

- Severity: High
- Area: Expression parser
- Steps to reproduce:
  1. Open calculator page.
  2. Click `(`, `2`, `+`, `3`, `)`, `×`, `4`, `=`.
- Expected: Display shows `20`.
- Actual: Display shows `2`.
- Impact: Complex expressions cannot be trusted.
- Evidence: `target/screenshots/parenthesesShouldPreserveExpressionOrder.png`

### CALC-005 - `sin` function returns incorrect value

- Severity: High
- Area: Scientific functions
- Steps to reproduce:
  1. Open calculator page.
  2. Click `1`, `sin`.
- Expected: Display shows approximately `0.8414709848` (radian mode).
- Actual: Display shows `1`.
- Impact: Scientific calculations are incorrect.
- Evidence: `target/screenshots/trigFunctionShouldComputeJavaMathEquivalent.png`

### CALC-006 - Empty evaluation returns `undefined`

- Severity: Medium
- Area: Error handling / UX
- Steps to reproduce:
  1. Open calculator page.
  2. Ensure display is empty (`C`).
  3. Click `=`.
- Expected: Display stays empty or shows a clear validation message.
- Actual: Display shows `undefined`.
- Impact: User-facing error text leaks implementation detail and reduces trust.
- Evidence: `target/screenshots/pressingEqualsOnEmptyDisplayShouldNotShowUndefined.png`

## Additional Notes

- Scientific functions `cos`, `tan`, `sqrt`, and `log` passed in this run.
- Based on behavior and source inspection, several defects are likely mapping/parser logic issues rather than UI rendering issues.
