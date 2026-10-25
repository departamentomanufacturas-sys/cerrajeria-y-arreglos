# Cerrajeria y Arreglos

A basic REST backend for a locksmith and repair service, built with Java 21, Spring Boot, Spring Web MVC, Spring Data JPA, and H2.

## Getting started

You will need a Java 21 JDK. From the project directory, start the application with the Maven wrapper:

```powershell
.\mvnw.cmd spring-boot:run
```

The server runs at `http://localhost:8080` by default. The project uses an in-memory H2 database, so its data is cleared when the application stops. The H2 console is available at `http://localhost:8080/h2-console` with the default JDBC URL `jdbc:h2:mem:cerrajeria` and username `sa` (blank password).

To connect to a different database, set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` before starting the application. For example, in PowerShell:

```powershell
$env:DB_URL = "jdbc:h2:file:./data/cerrajeria"
$env:DB_USERNAME = "sa"
$env:DB_PASSWORD = "your-password"
.\mvnw.cmd spring-boot:run
```

## API

All routes use the `/api/requests` base path.

| Method | Route | Description |
| --- | --- | --- |
| `GET` | `/api/requests` | List service requests (currently returns a placeholder greeting) |
| `GET` | `/api/requests/{id}` | Get a request by ID |
| `POST` | `/api/requests` | Create a request |
| `PUT` | `/api/requests/{id}` | Update a request |
| `DELETE` | `/api/requests/{id}` | Delete a request |

Create and update requests accept JSON with the following fields. All fields are required; `serviceType` must be `LOCKSMITH` or `REPAIR`.

```json
{
  "customerName": "Alex Smith",
  "phone": "+1 555 0100",
  "serviceType": "LOCKSMITH",
  "description": "Replace the front door lock"
}
```

Example request:

```powershell
Invoke-RestMethod -Method Post `
  -Uri http://localhost:8080/api/requests `
  -ContentType "application/json" `
  -Body '{"customerName":"Alex Smith","phone":"+1 555 0100","serviceType":"LOCKSMITH","description":"Replace the front door lock"}'
```

## Project structure

- `request/ServiceRequestController` exposes the HTTP endpoints.
- `request/ServiceRequestService` contains the request operations.
- `request/ServiceRequestRepository` provides JPA persistence.
- `request/ServiceRequest` defines the database entity.
- `request/ServiceRequestInput` validates incoming request data.
