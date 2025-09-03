# Helmes Task (Denni Karin)

A production-ready Spring Boot service for Helmes Test task built with Gradle, containerized with Docker. This README explains how to run it using **Docker (recommended)** or **Gradle**.

## Prerequisites

- **Java 21** (only needed for local Gradle runs)
- **Docker** and **Docker Compose** (v2)

The project is organized as a multi-module Gradle build. The main Spring Boot app lives under `app/`.

---

## Run with Docker

### 1) Start everything

```bash
docker compose up -d
```

This will:
- Build the application image (using the provided `Dockerfile`).
- Start a **PostgreSQL** database and the **app**.
- Expose the app on `http://localhost:${APP_PORT}` (default `8080`).

### 2) Verify it’s running

Open:
- `http://localhost:8080/` – main app

> Stop all containers with `docker compose down`.

---

## Run with Gradle

> This option runs the app directly on your machine without Docker. You’ll need a running PostgreSQL. Set up your credentials in `application.yml`.

### 1) Build

```bash
./gradlew clean build
```

### 2) Run the app

From the repo root:

```bash
# Run via Spring Boot plugin
./gradlew :app:bootRun
```

The app will start on `http://localhost:8080` by default. Use environment variables (below) to point it at your DB.

---

## Database & restoring the dump

A Postgres dump file is included in the repo (`helmes_20250904_011554.dump`).
