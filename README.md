# Bank

### Execute

- Create new account
````
curl --location --request POST 'http://localhost:8080/accounts' \
--header 'Content-Type: application/json' \
--data-raw '{
    "document_number": "12345678900"
}'
````

- Find account

````
curl --location --request GET 'http://localhost:8080/accounts/1'
````

- Create transaction
````

curl --location --request POST 'http://localhost:8080/transactions' \
--header 'Content-Type: application/json' \
--data-raw '{
    "account_id": 2,
    "operation_type_id": 4,
    "amount": 123.45
}'
````