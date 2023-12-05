# Vulnerable Expense Tracker API

  <img src="./vulnerable-api.png" alt="drawing" width="500"/>

## Syllabus
- [Description](#description)
- [Vulnerabilities](#vulnerabilities)
- [Installation](#installation)
- [Swagger Documentation](#swagger-documentation)
- [Postman Collection & Environment](#postman-collection--environment)

 ## Description

                                 




## Vulnerabilities
1. IDOR (3 Different Case)
2. Rate Limiting
3. SSRF (Request Body & URI)
4. Sensitive Information on HTTP Response
5. Misconfigured HTTP Headers
6. JWT Vulnerabilities
7. Business Logic Data Validation
8. Improper Error Handling

## Installation
1. Clone the repository
```shell
git clone https://github.com/kullaniciadi/awesome-api.git
```
2. Docker Compose
```shell
cd cd Expense\ Tracker\ API/ 
docker-compose up 
```
3. Check docker container for up and running

![img.png](img.png)
4. After successfully executing docker compose yml you should access the application on http://localhost:8080/api/v1. For create a test user to begin working on this app, you need to call `/register` endpoint. 


## Swagger Documentation
http://localhost:8080/api/v1/swagger-ui.html#/

![expense-swagger.png](..%2F..%2F..%2FDownloads%2Fexpense-swagger.png)     


## Postman Collection & Environment
You can find the [postman collection](/Expense%20Tracker%20API.postman_collection.json) and [postman environment](/Expense%20Tracker%20Env.postman_environment.json) in the repository.

![postman-expense.png](..%2F..%2F..%2FDownloads%2Fpostman-expense.png)


