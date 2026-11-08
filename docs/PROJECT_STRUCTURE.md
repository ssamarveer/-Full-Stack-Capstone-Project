# Project structure

- `frontend/` React UI, routes, forms, charts and responsive styling
- `backend/` Spring Boot REST API, JWT security, JPA entities, repositories and controllers
- `docker-compose.yml` PostgreSQL, Redis, backend and frontend services
- `docs/API_TESTING.md` Postman smoke-test sequence

Core business logic is in controllers and dashboard/insight endpoints. Financial record queries are always scoped to the authenticated user.
