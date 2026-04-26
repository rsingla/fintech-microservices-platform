# Deploy `bankservice.apicode.io`

This deployment exposes the banking showcase service at `https://bankservice.apicode.io` with automatic TLS, a portfolio landing page, Swagger UI, OpenAPI JSON, health probes, PostgreSQL, and Actuator metrics.

## DNS

Create an `A` record:

```text
bankservice.apicode.io -> <server-public-ip>
```

The server must allow inbound ports `80` and `443`.

## Server Prerequisites

- Docker Engine
- Docker Compose plugin
- Git

## First Deploy

```bash
git clone https://github.com/rsingla/fintech-microservices-platform.git
cd fintech-microservices-platform
cp .env.example .env
```

Edit `.env` and set:

- `POSTGRES_PASSWORD` to a long random production secret.
- `ACME_EMAIL` to the email used for Let's Encrypt notices.
- `SHOWCASE_REPOSITORY_URL` to the public GitHub repository.
- `SHOWCASE_WEBSITE_URL` to the main portfolio website.

Then deploy:

```bash
chmod +x scripts/deploy-bankservice.sh
./scripts/deploy-bankservice.sh
```

## Public Showcase URLs

- Landing page: `https://bankservice.apicode.io/`
- Swagger UI: `https://bankservice.apicode.io/docs`
- OpenAPI JSON: `https://bankservice.apicode.io/v3/api-docs`
- Showcase JSON: `https://bankservice.apicode.io/api/v1/showcase`
- Health: `https://bankservice.apicode.io/actuator/health`

## Portfolio Embed

Use these links on `apicode.io`:

```html
<a href="https://bankservice.apicode.io/">Live Banking Service</a>
<a href="https://bankservice.apicode.io/docs">Swagger UI</a>
<a href="https://bankservice.apicode.io/v3/api-docs">OpenAPI JSON</a>
<a href="https://github.com/rsingla/fintech-microservices-platform">Source Code</a>
```

## Operations

Check status:

```bash
docker compose --env-file .env -f docker/compose.bankservice.yml ps
```

View logs:

```bash
docker compose --env-file .env -f docker/compose.bankservice.yml logs -f banking-service caddy
```

Redeploy after code changes:

```bash
git pull
./scripts/deploy-bankservice.sh
```
