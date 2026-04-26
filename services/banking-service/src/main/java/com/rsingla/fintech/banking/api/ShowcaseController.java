package com.rsingla.fintech.banking.api;

import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ShowcaseController {
    private final String publicBaseUrl;
    private final String repositoryUrl;
    private final String websiteUrl;

    ShowcaseController(
        @Value("${showcase.public-base-url}") String publicBaseUrl,
        @Value("${showcase.repository-url}") String repositoryUrl,
        @Value("${showcase.website-url}") String websiteUrl
    ) {
        this.publicBaseUrl = publicBaseUrl;
        this.repositoryUrl = repositoryUrl;
        this.websiteUrl = websiteUrl;
    }

    @GetMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
    String landingPage() {
        return """
            <!doctype html>
            <html lang="en">
            <head>
              <meta charset="utf-8">
              <meta name="viewport" content="width=device-width, initial-scale=1">
              <title>Fintech Microservices Platform</title>
              <style>
                :root { color-scheme: dark; font-family: Inter, ui-sans-serif, system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif; }
                body { margin: 0; background: radial-gradient(circle at top left, #1f4ed8, #080b14 42%, #030712); color: #edf3ff; }
                main { max-width: 1040px; margin: 0 auto; padding: 72px 24px; }
                .eyebrow { color: #93c5fd; font-size: 14px; font-weight: 700; letter-spacing: .18em; text-transform: uppercase; }
                h1 { font-size: clamp(42px, 8vw, 84px); line-height: .92; margin: 18px 0; letter-spacing: -.06em; }
                p { color: #cbd5e1; font-size: 19px; line-height: 1.7; max-width: 760px; }
                .actions { display: flex; flex-wrap: wrap; gap: 14px; margin: 34px 0; }
                a { color: inherit; }
                .button { border: 1px solid rgba(255,255,255,.2); border-radius: 999px; padding: 13px 18px; text-decoration: none; background: rgba(255,255,255,.08); backdrop-filter: blur(18px); }
                .primary { background: #60a5fa; color: #07111f; border-color: #60a5fa; font-weight: 800; }
                .grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(220px, 1fr)); gap: 16px; margin-top: 44px; }
                .card { background: rgba(15, 23, 42, .72); border: 1px solid rgba(148,163,184,.24); border-radius: 24px; padding: 22px; box-shadow: 0 24px 90px rgba(0,0,0,.28); }
                .card h2 { margin: 0 0 10px; font-size: 18px; }
                .card p { margin: 0; font-size: 15px; }
                code { color: #bfdbfe; }
              </style>
            </head>
            <body>
              <main>
                <div class="eyebrow">bankservice.apicode.io</div>
                <h1>Modern Java fintech, built to be inspected.</h1>
                <p>
                  A Java 21 Spring Boot banking service from the Fintech Microservices Platform.
                  It demonstrates clean architecture, PostgreSQL persistence, Flyway migrations,
                  OpenAPI docs, Actuator health, and deployment-ready containerization.
                </p>
                <div class="actions">
                  <a class="button primary" href="/docs">Explore Swagger UI</a>
                  <a class="button" href="/v3/api-docs">OpenAPI JSON</a>
                  <a class="button" href="/api/v1/showcase">Showcase API</a>
                  <a class="button" href="%s">Source Code</a>
                  <a class="button" href="%s">Main Website</a>
                </div>
                <section class="grid">
                  <article class="card">
                    <h2>Banking APIs</h2>
                    <p><code>POST /api/v1/accounts</code>, deposits, withdrawals, pagination, and overdraft-safe rules.</p>
                  </article>
                  <article class="card">
                    <h2>Clean Architecture</h2>
                    <p>Boundaries are split into API, application, domain, and infrastructure packages.</p>
                  </article>
                  <article class="card">
                    <h2>Production Ready</h2>
                    <p>Docker, Caddy TLS, health probes, metrics, PostgreSQL, and CI are included.</p>
                  </article>
                  <article class="card">
                    <h2>Platform Story</h2>
                    <p>Part of a broader profile, banking, lending, and product/pricing showcase.</p>
                  </article>
                </section>
              </main>
            </body>
            </html>
            """.formatted(repositoryUrl, websiteUrl);
    }

    @GetMapping("/api/v1/showcase")
    ShowcaseResponse showcase() {
        return new ShowcaseResponse(
            "fintech-microservices-platform",
            "banking-service",
            publicBaseUrl,
            repositoryUrl,
            websiteUrl,
            Instant.now(),
            List.of(
                "Java 21",
                "Spring Boot 3.4",
                "Clean Architecture",
                "PostgreSQL and Flyway",
                "OpenAPI Swagger UI",
                "Micrometer and Actuator",
                "Docker and Caddy deployment"
            ),
            List.of(
                new ShowcaseLink("Landing page", publicBaseUrl + "/"),
                new ShowcaseLink("Swagger UI", publicBaseUrl + "/docs"),
                new ShowcaseLink("OpenAPI JSON", publicBaseUrl + "/v3/api-docs"),
                new ShowcaseLink("Health", publicBaseUrl + "/actuator/health"),
                new ShowcaseLink("Repository", repositoryUrl)
            )
        );
    }

    record ShowcaseResponse(
        String platform,
        String service,
        String publicBaseUrl,
        String repositoryUrl,
        String websiteUrl,
        Instant generatedAt,
        List<String> highlights,
        List<ShowcaseLink> links
    ) {
    }

    record ShowcaseLink(String label, String href) {
    }
}
