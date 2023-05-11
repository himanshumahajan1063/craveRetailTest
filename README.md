# Spring Boot "Microservice" Example Project

- This is a sample Java / Maven / Spring Boot application that can be used as a starter for creating a user specific microservice
- There are some external libraries used in the project to remove boiler place code and improve readability
- Some of external libraries are log4j and lombok
- Swagger documentation has been implemented
- Caching has been configured but not rigorously implemented
- Exception handling is done at service level
- data.sql file loads data in the beginning for some testing. It can be verified at h2 console as explained in below section for h2

## How to build

Run the command `mvn clean package`

## How to run the project

Run the command `mvn spring-boot:run`

## How to run tests

Run the command `mvn test`

## How to check the h2 database entries

Once you run the project, type on the URL
http://localhost:8080/h2-console/
Username - sa
Password - test

## How to check SWAGGER documentation

URL : http://localhost:8080/swagger-ui/index.html#/

Run the command `mvn spring-boot:run`

## API test sample endpoint

### Creating a user resource
`curl --location 'localhost:8080/crave-retail/users' \
--header 'Content-Type: application/json' \
--data '{
    "name" : "Himanshu",
    "age": 29
}`

## Improvements which could be done
- More rigorous testing
- Implement proper caching
- Implementing actual db like mysql instead of h2 database
- Exception handling can be improved but for this scope, it is ok

# Questions and Comments: himanshumahajan38@gmail.com

