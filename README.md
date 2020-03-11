# Resilience4j Spring Boot Demo Project (Using Maven)

It is a demo project, extraction of the [project](https://github.com/resilience4j/resilience4j-spring-boot2-demo)


## Project Setup

HomeController injects HomeService that then injects FailureService

The logic was to test that even if the FailureService fails, the HomeService should not be affected and the logic of circuit breaker.

## Contributors

@shah-smit
