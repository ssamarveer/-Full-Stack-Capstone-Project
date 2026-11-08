# SpendWise – Personal Finance & Budget Intelligence Platform

A full-stack capstone starter implementation based on the supplied B.E. CSE (AI & ML) synopsis.

## Stack
- Frontend: React + Vite + React Router + Recharts
- Backend: Java 25 (LTS), Spring Boot 3, Spring Security, JWT, Spring Data JPA
- Database: PostgreSQL
- Optional cache service: Redis (included in Docker Compose for future summary caching)

## Features
- Registration and login with BCrypt password hashing and JWT-protected APIs
- User-scoped income/expense transactions, search/filter, pagination and CSV export
- Dashboard totals and monthly summaries
- Monthly/category budgets with 80% threshold warnings
- Savings goals and contributions
- Rule-based spending insights (no trained AI model is claimed)
- Recurring transaction definitions
- Responsive React dashboard and charts

## Run with Docker (recommended)
1. Install Docker Desktop.
2. In this folder run: `docker compose up --build`
3. Open frontend: http://localhost:5173
4. Backend API: http://localhost:8080/api
5. PostgreSQL: localhost:5432 (database `spendwise`, user `spendwise`, password `spendwise`)

The frontend dev server proxies `/api` to the backend container. For local development without Docker, start PostgreSQL and run `mvn spring-boot:run` in `backend`, then run `npm install && npm run dev` inside `frontend`. To run the backend without PostgreSQL for local testing, use `mvn spring-boot:run -Dspring-boot.run.profiles=local` in `backend`; the local profile uses an in-memory H2 database, so its data is discarded when the backend stops.

## Local backend configuration
Environment variables (defaults are for local development):
- `DB_URL=jdbc:postgresql://localhost:5432/spendwise`
- `DB_USERNAME=spendwise`
- `DB_PASSWORD=spendwise`
- `JWT_SECRET=SpendWiseLocalDevelopmentSecretKeyChangeBeforeDeployment2026`
- `JWT_EXPIRATION=86400000`

## First use
Register an account from the app. Each account sees only its own records. Add income and expenses, then create budgets and savings goals.

## API overview
- `POST /api/auth/register`, `POST /api/auth/login`
- `GET/POST/PUT/DELETE /api/transactions`
- `GET /api/dashboard/summary`
- `GET/POST/PUT/DELETE /api/budgets`
- `GET/POST/PUT/DELETE /api/goals`; `POST /api/goals/{id}/contributions`
- `GET/POST/PUT/DELETE /api/recurring`
- `GET /api/insights`
- `GET /api/transactions/export`

All routes except registration/login and health require `Authorization: Bearer <token>`.

## Notes
- PostgreSQL schema is created/updated by Hibernate for this academic demo (`ddl-auto=update`). For production, use Flyway/Liquibase migrations and managed secrets.
- Redis is included as an optional service; core functionality does not depend on it.
- Automated creation of future recurring entries is intentionally not enabled. Recurring items are definitions managed by the user, matching the synopsis requirement for controlled behavior.
- Update JWT secrets and database credentials before public deployment.

## Team
Samarveer Singh (24BAI70674) · Yogesh Kumar (24BAI70649)
