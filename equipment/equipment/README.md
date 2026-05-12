# Equipment Microservice (GAMEDEV)

**Autor / Author:** Andrés Pestana Hidalgo

Este microservicio gestiona **equipo** (armas, armaduras y cascos) y sus bonificaciones.

---

## ES — Resumen

### Tecnologías
- Java 17
- Spring Boot
- Maven

### Puerto
- **8081** → `http://localhost:8081`

### Ejecución (Windows)

**PowerShell**
```powershell
cd C:\dev\gamedev\equipment\equipment
mvn spring-boot:run
```

**CMD**
```bat
cd /d C:\dev\gamedev\equipment\equipment && mvn spring-boot:run
```

> Nota: este servicio debe usar el puerto 8081 para no colisionar con `characters` (8080) y para que `loadout` lo consuma correctamente.

---

## EN — Summary

This microservice manages **equipment** (weapons, armors and helmets) and their bonuses.

### Tech stack
- Java 17
- Spring Boot
- Maven

### Port
- **8081** → `http://localhost:8081`

### Run (Windows)

**PowerShell**
```powershell
cd C:\dev\gamedev\equipment\equipment
mvn spring-boot:run
```

**CMD**
```bat
cd /d C:\dev\gamedev\equipment\equipment && mvn spring-boot:run
```

> Note: this service must run on port 8081 to avoid clashing with `characters` (8080) and to match `loadout` configuration.
