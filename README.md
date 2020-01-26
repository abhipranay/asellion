# Instructions
## Requirements
1. Docker
2. Docker compose
## Steps
1. bash start-services.sh
## API's
1. http://localhost:8080/api/v1/swagger-ui.html

## Example
#### Get Token
`curl -X POST \
  http://localhost:8080/api/v1/auth \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: c8473214-de48-989e-1799-a87f0ecef7c8' \
  -d '{
	"password": "password",
	"email": "user@email.com"
}'`

#### List Products

`curl -X GET \
     http://localhost:8080/api/v1/products \
     -H 'authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyQGVtYWlsLmNvbSIsImV4cCI6MTU4MDYzOTY0OSwiaWF0IjoxNTgwMDM0ODQ5fQ.uoHVkUnnZfo3ujtXNW3iJqAyvTP1tk4JXX6TBi7qxsWeyUwFn693xFCXcrm0BIKlCzWC-RfsDFM6zhcxjMae1w' \
     -H 'cache-control: no-cache' \
     -H 'postman-token: c460db7c-c9c8-a847-b6ef-25c619393534'`