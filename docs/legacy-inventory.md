# Legacy Inventory

The original repository was a merge of unrelated Java, Go, Python, TypeScript, and static documentation projects. The modernization intentionally keeps only the fintech showcase code in the public tree.

| Legacy folder | Disposition | Reason |
| --- | --- | --- |
| `profileservice` | Migrated | Profile/customer CRUD shape moved into `services/profile-service` with Java 21, Spring Boot 3, PostgreSQL, validation, Problem Details, and Testcontainers. |
| `mongoservice` | Reference only, then removed | Profile concepts reviewed, but Spring 3.1, JSP/XML MVC, Log4j 1.x, hardcoded Mongo credentials, and Java 1.6 were not carried forward. Rotate any credentials that were ever committed. |
| `springjpa-boot1-4` | Reference only, then removed | JPA persistence idea retained, but Boot 1.4 milestone, WAR packaging, H2 file DB, and `javax` APIs were retired. |
| `myretail` | Reimagined | Product/pricing idea becomes `services/product-service`; incomplete old implementation removed. |
| `bank` | Removed | Stub documentation without runnable source. |
| `bank-vxpw8w` | Removed | React/Ionic client is unrelated to the clean Java backend showcase. |
| `fileservice` | Removed | Go/S3 service is outside the Java fintech platform scope. |
| `todo-service` | Removed | Go todo sample is unrelated. |
| `campaign_manager` | Removed | Python demo is unrelated. |
| `workshop-spring-data-cassandra` | Removed | Cassandra workshop shell is unrelated and incomplete. |
| `jslint4java` | Removed | Vendored legacy JavaScript lint tooling is unrelated to fintech. |
| `jslint4java-docs` | Removed | Generated static documentation snapshot. |
| `springautowire` | Removed | Old Spring 3 XML/JSP DI demo is unrelated and obsolete. |
| `target/`, binaries, IDE metadata | Removed | Generated output and local IDE/build artifacts do not belong in the source tree. |

Baseline tag: `baseline-pre-modernization`. Modernization branch: `modernize/fintech-microservices-platform`.
