# ADR 0003: PostgreSQL as Primary Datastore

## Status
Accepted

## Decision
Use PostgreSQL as the primary datastore for profile, banking, lending, and product/pricing services in the first showcase slice.

## Consequences
The platform has one coherent local development and testing story. MongoDB and Firestore concepts from legacy services are not carried forward unless a future feature needs document storage.
