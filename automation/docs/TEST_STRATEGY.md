# QA Coverage Strategy - Scientific Calculator

## Quality Lens

Coverage is split into two execution layers:

- `sanity`: Fast checks for release gate confidence (load, input, basic arithmetic)
- `regression`: Broader functional checks for operator correctness, scientific functions, and precedence/parentheses

## Coverage Matrix

- UI rendering and input behavior
- Arithmetic operators (`+`, `-`, `*`, `/`)
- Decimal and numeric input mapping
- Parentheses parsing and precedence
- Scientific operations (`sin`, `cos`, `tan`, `sqrt`, `log`)
- Error behavior for invalid states

## Out of Scope (Current Cycle)

- Cross-browser visual diffs
- Accessibility audits (keyboard-only and screen readers)
- Performance and load behavior

## Scaling Plan

- Add API-independent contract tests for parser logic by extracting the evaluator into testable units
- Add browser matrix in CI (`chrome`, `firefox`)
- Add nightly regression including edge/boundary data sets

## Execution Profiles

- Full suite: `mvn clean test -Dheadless=true -Dbrowser=chrome`
- Sanity suite: `mvn clean test -Psanity -Dheadless=true`
- Regression suite: `mvn clean test -Pregression -Dheadless=true`

## Current Status

- Latest full run (27 Apr 2026): 15 executed, 9 passed, 6 failed
- Regression failures are tracked as known defects in `docs/BUG_REPORT.md`
