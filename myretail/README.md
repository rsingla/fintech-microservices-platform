# MyRetail

RESTful product API that aggregates product data from Target's API and pricing from MongoDB.

## APIs

- `GET /v1/product/{id}` - Retrieve product by ID (name from Target API + price from MongoDB)
- `POST /v1/product/` - Insert product
- `PUT /v1/product/` - Update product price
- `DELETE /v1/product/{id}` - Delete product (test profile only)

## Setup

### Prerequisites

- Java 17+
- Maven 3.6+
- MongoDB (local or Atlas)

### Environment Variables

**Do not commit secrets.** Set these before running:

```bash
# MongoDB credentials
export MONGODB_HOST=your-cluster.mongodb.net
export MONGODB_PORT=27017
export MONGODB_USERNAME=your_username
export MONGODB_PASSWORD=your_password
export MONGODB_DATABASE=retail_product

# Or use Atlas URI (when using Spring Boot auto-config):
# export SPRING_DATA_MONGODB_URI="mongodb+srv://user:pass@cluster.mongodb.net/retail_product?retryWrites=true&w=majority"

# Target Products API key
export API_TARGET_KEY_VALUE=your_target_api_key
```

Copy `.env.example` to `.env` and fill in values for local development. Load with `source .env` before `mvn spring-boot:run`.

### Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

Or with explicit env vars:

```bash
export SPRING_DATA_MONGODB_URI="mongodb+srv://..."
export API_TARGET_KEY_VALUE="..."
mvn spring-boot:run
```

### Endpoints

- API base: `http://localhost:8080/v1/product/`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI docs: `http://localhost:8080/v3/api-docs`
- Actuator health: `http://localhost:8080/actuator/health`
