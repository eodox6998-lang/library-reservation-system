## Library Book Reservation System — a Spring Boot app demonstrating dependency injection, profiles, and bean lifecycle. 


## Tech Stack
Java 21, Spring Boot 4.1.1, Maven 4.0.0

## Prerequisites

1. Java version:- 21 or above
2. Maven 4.0+

## How to run

Default: mvn spring-boot:run
Test profile: mvn spring-boot:run -Dspring-boot.run.profiles=test

switches PaymentGatewayImpl → MockPaymentGateway

## Endpoints

| Method |                   Path                  |Description             |
|--------|-----------------------------------------|------------------------|
| GET    | /api/reservations                       | Get all reservations   |
| POST   | /api/reserve                            | create a reservation   |
| POST   | /api/reservations/{id}/{member}/cancel  | cancel a reservation   |
| POST   | /api/reservations/{id}/{member}/confirm | confirms a reservation | 
| POST   | /api/reservations/{id}/{member}/return  | returns the book       | 

##  Architecture

```                         
+--------------------+       +----------------+
| ReservationService |------>| PaymentGateway |
+--------------------+       +----------------+      
                                    |
                          +---------+-----------+                                  
                          |                     |
                          v                     v
                    +--------------------+  +--------------------+
                    | PaymentGatewayImpl |  | MockPaymentGateway |              
                    +--------------------+  +--------------------+
```
## Key Concepts Demonstrated

Constructor injection
@Profile switching
@Bean for third-party classes
@PostConstruct / @PreDestroy
@Configuration + @Bean for RestTemplate

## Project Structure

```
src/main/java/com/example/demo/
├── DemoApplication.java
├── Book.java
├── BookRepository.java
├── BookListInitializer.java
├── Member.java
├── MemberRepository.java
├── Intailizer.java
├── Reservation.java
├── ReserveRequest.java
├── ReservationService.java
├── NotificationService.java
├── PaymentGateway.java
├── PaymentGatewayImpl.java
├── MockPaymentGateway.java
├── AuditLogger.java
└── Timestamp.java
```   

## Notes / Stretch Goals

- **Second profile** (`dev` / `prod`) — different DB configs, logging levels, etc.
- **`@Profile("test-fail")`** — swap `MockPaymentGateway` for a fake that always fails, to test error paths