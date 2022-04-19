# bookshop
Book store Rest API

## Run integration tests
./gradlew clean test

## Run Book store app
./gradlew clean bootRun

## H2 Database console
H2 database is initialized with a sample of books, customers and addresses. 
Refer to application-<profile>.yml for settings.

## Testing
Consult OpenAPI (Swagger) documentation for Book store application at 
http://ec2-18-233-200-228.compute-1.amazonaws.com:8080/swagger-ui.html
The swagger page will need the following username/password.
username: admin
password: absa-password
 
#### List Book Store Data
jq is a linux program that formats json output for better readability. Testing can still be done without it.
```
curl -v  http://admin:absa-password@ec2-18-233-200-228.compute-1.amazonaws.com:8080/booklist | jq '.' 
```
#### Add Books To Cart
```
curl -v -X POST http://admin:absa-password@ec2-107-23-96-201.compute-1.amazonaws.com:8080/addToCart -H 'Content-Type: application/json' -d '{"bookId": 3, "quantity": 1, "customerId": 3}'
```
#### (Optional) List Cart Contents
```
curl -v http://admin:absa-password@ec2-18-233-200-228.compute-1.amazonaws.com:8080/3/getCart | jq '.'
```

#### Place Order
```
curl -v -X POST http://admin:absa-password@ec2-18-233-200-228.compute-1.amazonaws.com:8080/3/placeOrder
```
#### List Orders
```
curl -v http://admin:absa-password@ec2-18-233-200-228.compute-1.amazonaws.com:8080/3/orderlist | jq '.'
```

