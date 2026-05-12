# GAMEDEV — Microservices (Characters, Equipment, Loadout)

**Author:** Andrés Pestana Hidalgo

Project composed of **three microservices**. The first two expose data (Characters and Equipment) and the third one (**Loadout**) acts as an **integrator**, consuming the other two to build a “loadout” (character + equipment + final stats).

---

## Table of contents

- [Documentation](#documentation)
- [Tech stack](#tech-stack)
- [Architecture (high level)](#architecture-high-level)
- [How to run locally](#how-to-run-locally)
- [Ports and base URLs](#ports-and-base-urls)
- [APIs (quick overview)](#apis-quick-overview)
- [Notes](#notes)

---

## Documentation

- **EN (HTML):** [`docs/DOCUMENTATION_EN.html`](docs/DOCUMENTATION_EN.html)
- **Loadout OpenAPI (YAML):** [`loadout/loadout/src/main/resources/openapi.yaml`](loadout/loadout/src/main/resources/openapi.yaml)

> Note: `loadout` includes an OpenAPI specification. For the other two microservices, the API is best understood by reading their REST controllers.

---

## Tech stack

- **Java 17**
- **Spring Boot** (web / MVC)
- **Maven** (build)
- **Hexagonal architecture (Ports & Adapters)** + **SOLID** principles
- **Tests** with Spring Boot Test
- **MapStruct** (DTO ↔ domain mapping)
- **Lombok** (boilerplate reduction)
- **PostgreSQL** (real persistence for `characters` and `equipment`)
- **DBeaver** (client to inspect/manage the database)
- **OpenAPI/Swagger** (defined for `loadout`)

---

## Architecture (high level)

- **characters**  
  Manages characters and their base stats (persisted in **PostgreSQL**).

- **equipment**  
  Manages equipment pieces (weapons, armor, helmets) and their bonuses.

- **loadout (integrator)**  
  Orchestrates calls to **characters** and **equipment** to:
  - fetch the character
  - fetch its equipment
  - compute final stats
  - create/update a loadout

---

## How to run locally

This repository contains **three Maven projects** (one per microservice). The simplest approach is to open **3 terminals** and run them separately.

### Requirement: PostgreSQL + DBeaver

- PostgreSQL must be running on `localhost:5432`
- Databases: `characters_db` and `equipment_db`
- Use **DBeaver** to connect to PostgreSQL and refresh schemas/tables to see the data

If you cannot see tables in DBeaver, make sure you don't have an active filter (it appears as **“Filtered by search”** in the tree) and press **F5** to refresh.

### 1) Start `characters` (port 8080)

**PowerShell (recommended on Windows):**
```powershell
cd C:\dev\gamedev\characters\characters
mvn spring-boot:run
```

**CMD (`&&` is allowed):**
```bat
cd /d C:\dev\gamedev\characters\characters && mvn spring-boot:run
```

> Important: in **PowerShell**, do NOT copy the `&&` line. Use the **PowerShell** block (2 lines).

### 2) Start `equipment` (port 8081)

**PowerShell:**
```powershell
cd C:\dev\gamedev\equipment\equipment
mvn spring-boot:run
```

**CMD:**
```bat
cd /d C:\dev\gamedev\equipment\equipment && mvn spring-boot:run
```

> Important: in **PowerShell**, do NOT copy the `&&` line. Use the **PowerShell** block (2 lines).

### 3) Start `loadout` (port 8082)

**PowerShell:**
```powershell
cd C:\dev\gamedev\loadout\loadout
mvn spring-boot:run
```

**CMD:**
```bat
cd /d C:\dev\gamedev\loadout\loadout && mvn spring-boot:run
```

> Important: in **PowerShell**, do NOT copy the `&&` line. Use the **PowerShell** block (2 lines).

> Alternative: you can also build and run the `.jar` of each service with `mvn package` and `java -jar target\*.jar`, but `spring-boot:run` is the quickest start.

---

## Ports and base URLs

- **characters:** `http://localhost:8080`
- **equipment:** `http://localhost:8081`
- **loadout:** `http://localhost:8082`

`loadout` is configured to consume:

- Characters: `http://localhost:8080`
- Equipment: `http://localhost:8081`

(See: `loadout/loadout/src/main/resources/application.yaml`)

---

## APIs (quick overview)

### Loadout (integrator)

Main endpoints (defined in OpenAPI):

- `GET /characters/{id}/equipment` — returns character + equipment + final stats
- `POST /characters/{id}/equipment` — assigns equipment to a character
- `PUT /characters/{id}/equipment` — updates an existing loadout

Base URL example:

- `http://localhost:8082`

---

## Notes

- `characters` and `equipment` use **PostgreSQL**. Data **persists** across restarts.
- To inspect data / run queries, use **DBeaver** connected to `localhost:5432`.
- For more detail (flows, DTOs, JSON examples), open the HTML documentation under `/docs`.
