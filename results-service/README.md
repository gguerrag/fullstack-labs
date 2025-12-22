# Results Service

Microservicio desarrollado en Spring Boot para la gestión de resultados de exámenes clínicos.

## Tecnologías
- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Oracle JDBC
- Maven
- JaCoCo
- Docker

## Arquitectura
- Controller
- Service
- Repository
- GlobalExceptionHandler
- Tests unitarios y de controlador

## Perfiles
- application-results.yml
- application-docker.yml

## Tests y cobertura
```bash
mvn test
mvn test jacoco:report
