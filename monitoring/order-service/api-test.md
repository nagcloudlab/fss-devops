curl -w '\n' -X POST http://localhost:8080/orders \
-H "Content-Type: application/json" \
-d '{"orderId":"123", "amount":500.0}'
