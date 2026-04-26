# Website Showcase Embed

Use this content on `apicode.io` to make the live banking service and code visible.

## Suggested Section Copy

```html
<section>
  <p>Live Java 21 Spring Boot fintech service</p>
  <h2>Banking Service Showcase</h2>
  <p>
    A production-style banking API with account opening, deposits, withdrawals,
    PostgreSQL persistence, Flyway migrations, OpenAPI docs, health probes,
    Docker deployment, and clean architecture boundaries.
  </p>
  <a href="https://bankservice.apicode.io/">Live Demo</a>
  <a href="https://bankservice.apicode.io/docs">Swagger UI</a>
  <a href="https://bankservice.apicode.io/v3/api-docs">OpenAPI JSON</a>
  <a href="https://github.com/rsingla/fintech-microservices-platform">Source Code</a>
</section>
```

## API Links

- Landing page: `https://bankservice.apicode.io/`
- Swagger UI: `https://bankservice.apicode.io/docs`
- OpenAPI JSON: `https://bankservice.apicode.io/v3/api-docs`
- Showcase metadata: `https://bankservice.apicode.io/api/v1/showcase`
- Health endpoint: `https://bankservice.apicode.io/actuator/health`

## Code Paths To Highlight

- Banking API: `services/banking-service/src/main/java/com/rsingla/fintech/banking/api`
- Banking business rules: `services/banking-service/src/main/java/com/rsingla/fintech/banking/application`
- Banking domain model: `services/banking-service/src/main/java/com/rsingla/fintech/banking/domain`
- Persistence adapter: `services/banking-service/src/main/java/com/rsingla/fintech/banking/infrastructure`
- Deployment stack: `docker/compose.bankservice.yml`
- TLS reverse proxy: `docker/Caddyfile`
