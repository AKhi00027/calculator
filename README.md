# Calculator Workspace

Repository layout:

- `app/` contains the calculator web artifact (`calculator_obfuscated.html`).
- `automation/` contains the Selenium + TestNG automation project.
- `.github/workflows/` contains CI workflow definitions.

## Run Automation

From repository root:

```bash
cd automation
mvn clean test -Dheadless=true -Dbrowser=chrome
```

Headed run with slower visible steps:

```bash
cd automation
mvn clean test -Dheadless=false -Dbrowser=chrome -DstepDelayMs=700
```

## Submission Summary

This submission contains a CI-ready end-to-end UI automation framework for the Scientific Calculator.

### Deliverables Included

- Automated test suite with `sanity` and `regression` coverage
- Bug findings with professional defect documentation
- Execution reports and failure screenshots
- Full Git commit history with incremental logical commits

### Documentation

- Test strategy: `automation/docs/TEST_STRATEGY.md`
- Bug report: `automation/docs/BUG_REPORT.md`
- Submission text: `SUBMISSION.md`

### Run Commands

```bash
cd automation
mvn clean test -Dheadless=true -Dbrowser=chrome
mvn clean test -Psanity -Dheadless=true
mvn clean test -Pregression -Dheadless=true
```

### CI Readiness

Tests run headlessly without manual intervention and are configured for CI execution via GitHub Actions.

### Latest Execution Snapshot

- Date: 27 Apr 2026 (IST)
- Command: `mvn clean test -Dheadless=true -Dbrowser=chrome`
- Result: 15 run, 9 passed, 6 failed (known product defects documented in `automation/docs/BUG_REPORT.md`)
