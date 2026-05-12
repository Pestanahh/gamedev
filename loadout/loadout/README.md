# Loadout Microservice (GAMEDEV) — Integrator

**Autor / Author:** Andrés Pestana Hidalgo

Este microservicio es el **integrador** del sistema: consume `characters` y `equipment` para devolver un **loadout** completo (personaje + equipo + estadísticas finales).

---

## ES — Resumen

### Tecnologías
- Java 17
- Spring Boot
- Maven
- OpenAPI (ver `src/main/resources/openapi.yaml`)

### Puerto
- **8082** → `http://localhost:8082`

### Servicios que consume
Configurado en `src/main/resources/application.yaml`:
- Characters: `http://localhost:8080`
- Equipment: `http://localhost:8081`

### Ejecución (Windows)

**PowerShell**
```powershell
cd C:\dev\gamedev\loadout\loadout
mvn spring-boot:run
```

**CMD**
```bat
cd /d C:\dev\gamedev\loadout\loadout && mvn spring-boot:run
```

### Convención de DTOs (REST)
- Entrada: `LoadoutInputDto` (`infrastructure/input/rest/dto/LoadoutInputDto.java`)
- Salida: `LoadoutResponseDto` (`infrastructure/input/rest/dto/LoadoutResponseDto.java`)

### Modelos en dominio (casos de uso)
- Entrada: `LoadoutInput` (`domain/model/LoadoutInput.java`)
- Salida: `Loadout` (`domain/model/Loadout.java`)

> Nota: el sufijo `Dto` se usa en la capa REST por convención; el dominio no expone DTOs de transporte.

---

## EN — Summary

This microservice is the system **integrator**: it calls `characters` and `equipment` and returns a unified **loadout** (character + equipment + final stats).

### Tech stack
- Java 17
- Spring Boot
- Maven
- OpenAPI (see `src/main/resources/openapi.yaml`)

### Port
- **8082** → `http://localhost:8082`

### Consumed services
Configured in `src/main/resources/application.yaml`:
- Characters: `http://localhost:8080`
- Equipment: `http://localhost:8081`

### Run (Windows)

**PowerShell**
```powershell
cd C:\dev\gamedev\loadout\loadout
mvn spring-boot:run
```

**CMD**
```bat
cd /d C:\dev\gamedev\loadout\loadout && mvn spring-boot:run
```

### DTO naming (REST)
- Input: `LoadoutInputDto` (`infrastructure/input/rest/dto/LoadoutInputDto.java`)
- Output: `LoadoutResponseDto` (`infrastructure/input/rest/dto/LoadoutResponseDto.java`)

### Domain models (use cases)
- Input: `LoadoutInput` (`domain/model/LoadoutInput.java`)
- Output: `Loadout` (`domain/model/Loadout.java`)

> Note: the `Dto` suffix is used in the REST layer by convention; the domain does not expose transport DTOs.
