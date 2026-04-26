# ADR 0004: AI-Assisted Lending Explanation Boundary

## Status
Accepted

## Decision
Keep loan approval decisions deterministic and auditable. Use Spring AI only at the explanation boundary, where model output can make a policy decision easier to understand without changing the decision.

## Consequences
The centerpiece demonstrates AI in a credible fintech pattern: deterministic rules produce approval, referral, or decline outcomes; AI can enrich language but cannot override policy or audit fields.
