# Scientific Calculator Automation Assignment Submission

## Submission Note

Please find this submission for the Scientific Calculator QA Automation assignment.

Repository includes:

- End-to-end automated test suite (sanity + regression)
- CI workflow for headless execution
- Test strategy and bug report documentation
- Reproducible reports and failure screenshots
- Full incremental commit history (no squash)

Project structure:

- `app/`: calculator artifact
- `automation/`: Java + Selenium + TestNG automation framework
- `automation/docs/TEST_STRATEGY.md`
- `automation/docs/BUG_REPORT.md`

Execution:

- Full suite: `cd automation && mvn clean test -Dheadless=true -Dbrowser=chrome`
- Sanity: `cd automation && mvn clean test -Psanity -Dheadless=true`
- Regression: `cd automation && mvn clean test -Pregression -Dheadless=true`

Latest full execution result (27 Apr 2026, 10:00 IST):

- Command: `mvn clean test -Dheadless=true -Dbrowser=chrome`
- Outcome: `15` tests run, `9` passed, `6` failed
- Status note: Failing cases are known product defects documented in `automation/docs/BUG_REPORT.md` with reproduction steps and evidence screenshots.

## Short Portal Description

Implemented a structured Selenium + TestNG automation framework with clear sanity vs regression separation, CI-ready headless execution, and defect reporting. Included reproducible bug evidence (steps, severity, impact, screenshots) and documented test strategy for coverage rationale and scalability.
