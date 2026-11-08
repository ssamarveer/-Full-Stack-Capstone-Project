# API smoke testing with Postman

1. `POST http://localhost:8080/api/auth/register` JSON: `{"name":"Demo User","email":"demo@example.com","password":"secret123"}`
2. Copy `token` from response. Add header `Authorization: Bearer <token>` to all following calls.
3. `POST /api/transactions` JSON: `{"amount":1200,"type":"EXPENSE","category":"Food","transactionDate":"2026-11-08","description":"Groceries","notes":"Weekly shopping"}`
4. `GET /api/dashboard/summary`
5. `POST /api/budgets` JSON: `{"month":"2026-11","category":"Food","limitAmount":5000}`
6. `POST /api/goals` JSON: `{"name":"Emergency fund","targetAmount":25000,"targetDate":"2027-06-30"}`
7. `POST /api/goals/1/contributions` JSON: `{"amount":1000}` (replace 1 with returned goal ID)
8. `GET /api/insights`, `GET /api/transactions/export`

Try registering a second user and verify that the first user's transactions cannot be accessed by the second user.
