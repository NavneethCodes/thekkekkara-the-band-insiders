# Thekkekkara The Band Insiders' App

An exclusive, full-stack gig management and scheduling ecosystem built specifically for **Thekkekkara Productions**.

This application provides strict role-based access control (RBAC) to streamline event creation, roster assignments, setlist management, and revenue tracking across web and mobile platforms.

---

# Tech Stack

| Layer              | Technologies                                                                                                                                                                                                                |
| ------------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Backend**        | [Java 21](https://docs.oracle.com/en/java/javase/21/), [Spring Boot 3](https://docs.spring.io/spring-boot/docs/3.0.0/reference/html/), [Spring Security](https://docs.spring.io/spring-security/reference/) (JWT)           |
| **Database**       | [PostgreSQL](https://www.postgresql.org/docs/), [Redis](https://redis.io/docs/latest/)                                                                                                                                      |
| **Web Frontend**   | [Angular 16](https://v16.angular.io/docs), [SCSS](https://sass-lang.com/documentation/), [Tailwind CSS v3](https://v3.tailwindcss.com/docs), [Bootstrap 5](https://getbootstrap.com/docs/5.3/getting-started/introduction/) |
| **Mobile App**     | [Flutter](https://docs.flutter.dev/), [Dart](https://dart.dev/guides)                                                                                                                                                       |
| **Infrastructure** | [Docker](https://docs.docker.com/), [Docker Compose](https://docs.docker.com/compose/)                                                                                                                                      |

---

# Core Features

## Role-Based Access Control (RBAC)

- JWT-based authentication and authorization
- Strict separation between **Managers** and **Members**
- Domain-level access control across the application

## Dynamic Calendars

### Managers

- View all events
- Create and manage gigs
- Assign performers

### Members

- View only assigned gigs
- Accept or decline invitations
- Track personal schedules

## Setlist Management

Manage complete performance information including:

- Song title
- BPM
- Scale / Key
- Assigned vocalist
- Assigned instrumentalists
- Performance order

## Wage & Revenue Tracking

Automatically manage:

- Accepted and declined performer invitations
- Performer-specific wages
- Gross revenue
- Net revenue calculations

## Performance

- Redis-backed caching
- Optimized REST APIs
- Target API response time below **2.5 seconds**

---

# Local Development Setup

Follow the steps below to run the complete application on a local Windows machine.

## Prerequisites

### Docker Desktop

Download and install Docker Desktop.

https://www.docker.com/products/docker-desktop/

Ensure **WSL2 Integration** is enabled in the Docker Desktop settings.

---

### Java 21 SDK

Download and install Java 21 (JDK).

https://adoptium.net/temurin/releases/?version=21

Configure your IDE to use Java 21.

---

### Node.js

Download and install the latest LTS release.

https://nodejs.org/

---

### Angular CLI

Install Angular CLI globally.

```bash
npm install -g @angular/cli
```

---

### Flutter SDK

Download Flutter.

https://docs.flutter.dev/get-started/install

Extract Flutter to a permanent directory such as:

```text
C:\src\flutter
```

Add the following directory to your system `PATH`:

```text
C:\src\flutter\bin
```

Verify the installation:

```bash
flutter doctor
```

---

### Android Studio

Download Android Studio.

https://developer.android.com/studio

Install:

- Android SDK
- Android Emulator
- Device Manager

---

# Running the Application

## 1. Start the Infrastructure

Navigate to the backend project directory.

Start PostgreSQL and Redis using Docker Compose.

```bash
docker-compose up -d
```

Verify that the containers are running.

```bash
docker ps
```

---

## 2. Start the Spring Boot Backend

Open the backend project in IntelliJ IDEA.

Allow Maven to resolve and download all dependencies.

Run the main application class or execute:

```bash
(For Linux)
set -a; source ./.env; set +a

(For Windows)
Get-Content .env | Where-Object { $_ -match '^\w+=' } | ForEach-Object { $name, $value = $_ -split '=', 2; Set-Item -Path "env:\$name" -Value $value }

spring-boot:run
OR
./mvnw spring-boot:run
```

The backend will be available at:

```text
http://localhost:8080
```

---

## 3. Start the Angular Web Application

Navigate to the `web` directory.

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
ng serve
```

Open the application in your browser:

```text
http://localhost:4200
```

---

## 4. Start the Flutter Mobile Application

Ensure an Android emulator is running or connect a physical Android device with USB debugging enabled.

Navigate to the `mobile` directory.

Install dependencies:

```bash
flutter pub get
```

Run the application:

```bash
flutter run
```

---

# Project Structure

```text
.
├── backend/          Spring Boot Backend
├── web/              Angular Web Application
├── mobile/           Flutter Mobile Application
├── docker/           Docker Compose Configuration
└── README.md
```

---

# Security

- JWT Authentication
- Role-Based Authorization
- Protected REST APIs
- Manager and Member access segregation
- Redis-backed caching

---

# Performance Targets

| Metric            | Target        |
| ----------------- | ------------- |
| API Response Time | < 2.5 seconds |
| Authentication    | JWT           |
| Database          | PostgreSQL    |
| Cache             | Redis         |

---

# License

Copyright © 2026 Thekkekkara Productions. All rights reserved.

Commercial distribution, modification, reproduction, reverse-engineering, or redistribution of this software, in whole or in part, is strictly prohibited without prior written consent from **Thekkekkara Productions**.
