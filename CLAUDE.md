# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**store-service** is a Micronaut-based microservice for managing car sales. It exposes a `/sales` endpoint to record sales transactions and communicates with an external vehicle service to fetch vehicle details.

- **Language**: Groovy with static compilation (`@CompileStatic`)
- **Framework**: Micronaut 4.10.13
- **Build Tool**: Gradle
- **Java Target**: Java 25
- **Runtime**: Netty
- **Test Framework**: JUnit 5
- **Port**: 8081 (configurable via `micronaut.server.port`)

## Architecture

The application follows a layered architecture:

```
Controller (HTTP endpoints) → Service (business logic) → Client (external calls)
                                         ↓
                                    DTOs (data models)
```

### Key Components

- **SalesController** (`src/main/groovy/br/com/carstore/controller/`): REST endpoint at `/sales` that accepts POST requests with sales data
- **SalesService** (`src/main/groovy/br/com/carstore/service/`): Singleton service that processes sales by fetching vehicle data from an external service and logging results
- **VehicleClient** (`src/main/groovy/br/com/carstore/client/`): Declarative HTTP client interface for communicating with the external vehicle service at `http://localhost:8080`
- **DTOs**:
  - `InputSalesDTO`: Request payload with client name, vehicle ID, sale value, and installment quantity
  - `VehicleDTO`: Response from vehicle service with vehicle details (id, model, brand, licensePlate)

All beans are managed via Micronaut's dependency injection using `@Singleton` and constructor injection.

## Build and Run Commands

```bash
# Clean build
./gradlew clean build

# Run the application
./gradlew run

# Run a single test
./gradlew test --tests StoreServiceTest

# Run all tests
./gradlew test

# Build native image (GraalVM)
./gradlew nativeImage

# Build Docker image (native)
./gradlew dockerfileNative
```

## Configuration

- **File**: `src/main/resources/application.properties`
- **Key properties**:
  - `micronaut.application.name`: Application name (store-service)
  - `micronaut.server.port`: Server port (8081)
  - `external.service.vehicle.url`: URL of the external vehicle service (http://localhost:8080)

External service URL is injected into `VehicleClient` using the `@Client("${external.service.vehicle.url}")` annotation.

## Logging

Uses SLF4J with Logback. Configure logging via `src/main/resources/logback.xml`. The service class uses `@Slf4j` for dependency-injected logger instances.

## Development Notes

- **Serialization**: Jackson-based serialization via `micronaut-serde-jackson`. All DTOs use `@Serdeable` for automatic JSON mapping
- **Validation**: HTTP validation support available via `micronaut-http-validation`
- **AOT Optimizations**: Enabled for native compilation (service loading, class loading, Netty optimization)
- **Package Structure**: All classes in `br.com.carstore.*` are subject to Micronaut annotation processing
