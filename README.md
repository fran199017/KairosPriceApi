## API_Test
### Overview
This is a test-based API built using hexagonal architecture, Java 17, and Spring Boot 3.2.0.  
It uses an in-memory H2 database and OpenAPI for API testing and documentation.

### Technologies Used
- Java 17  
- Spring Boot  
- H2 Database  
- OpenAPI (Swagger)  
- Flyway for database migrations  

### Requirements
- Java 17  
- Maven  

### Setup Instructions
1. Open the project at the root directory  
2. Run `mvn clean install`  
3. Run `mvn clean test` to generate test coverage (check the Jacoco report at `target/site/jacoco/index.html`)  
4. Execute the app:  
   `java -jar target/prueba-0.0.1-SNAPSHOT.jar`  

### H2 Database Access
- URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
- JDBC URL: `jdbc:h2:mem:testdb`  
- Username: `sa`  
- Password: *(leave empty)*  

### Swagger Documentation
- [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


