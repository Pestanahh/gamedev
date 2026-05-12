# Documentación del Microservicio Characters

## 1. Endpoints Disponibles

### Base URL
```
/characters
```

### Operaciones REST

#### 1.1 Obtener todos los personajes
- **Ruta:** `GET /characters`
- **Descripción:** Devuelve la lista completa de personajes
- **Respuesta exitosa:** `200 OK`
- **Cuerpo de respuesta:** `List<GameCharacterDto>`

#### 1.2 Obtener un personaje por ID
- **Ruta:** `GET /characters/{id}`
- **Descripción:** Devuelve un único personaje buscado por su ID
- **Parámetros de ruta:** 
  - `id` (Long) - ID del personaje
- **Respuesta exitosa:** `200 OK`
- **Cuerpo de respuesta:** `GameCharacterDto`

#### 1.3 Crear un personaje
- **Ruta:** `POST /characters`
- **Descripción:** Añade un nuevo personaje a la lista
- **Cuerpo de petición:** `GameCharacterDto` (sin ID)
- **Respuesta exitosa:** `201 CREATED`
- **Cuerpo de respuesta:** `GameCharacterDto` (con ID asignado)

#### 1.4 Actualizar un personaje
- **Ruta:** `PUT /characters/{id}`
- **Descripción:** Actualiza un personaje específico por su ID
- **Parámetros de ruta:** 
  - `id` (Long) - ID del personaje
- **Cuerpo de petición:** `GameCharacterDto`
- **Respuesta exitosa:** `200 OK`
- **Cuerpo de respuesta:** `GameCharacterDto` actualizado

#### 1.5 Eliminar un personaje
- **Ruta:** `DELETE /characters/{id}`
- **Descripción:** Elimina un personaje específico por su ID
- **Parámetros de ruta:** 
  - `id` (Long) - ID del personaje
- **Respuesta exitosa:** `204 NO CONTENT`

#### 1.6 Obtener personajes por estadísticas
- **Ruta:** `GET /characters/stats`
- **Descripción:** Obtiene una lista de personajes con salud mayor a X y defensa menor a Y
- **Parámetros de consulta (Query Params):**
  - `health` (Integer) - Salud mínima
  - `defense` (Integer) - Defensa máxima
- **Respuesta exitosa:** `200 OK`
- **Cuerpo de respuesta:** `List<GameCharacterDto>`

---

## 2. Modelo de Dominio: GameCharacter

### Campos

| Campo | Tipo | Descripción |
|-------|------|-------------|
| `id` | Long | Identificador único del personaje |
| `name` | String | Nombre del personaje |
| `health` | Integer | Puntos de salud del personaje |
| `attack` | Integer | Estadística de ataque |
| `defense` | Integer | Estadística de defensa |
| `speed` | Integer | Estadística de velocidad |

### Características
- La clase utiliza Lombok para generar automáticamente getters, setters, toString(), equals() y hashCode()
- Incluye constructores sin argumentos y con todos los argumentos

---

## 3. DTOs (Data Transfer Objects)

### GameCharacterDto

Este DTO se utiliza tanto para peticiones (request) como para respuestas (response) en todas las operaciones REST.

#### Campos

| Campo | Tipo | Validación | Descripción | Ejemplo |
|-------|------|------------|-------------|---------|
| `id` | Long | - | Identificador único (generado por el servidor) | 1 |
| `name` | String | @NotBlank | Nombre del personaje | "Butcher" |
| `health` | Integer | @NotNull | Puntos de salud | 100 |
| `attack` | Integer | @NotNull | Estadística de ataque | 10 |
| `defense` | Integer | @NotNull | Estadística de defensa | 10 |
| `speed` | Integer | @NotNull | Estadística de velocidad | 10 |

#### Validaciones
- **name**: No puede estar en blanco (mensaje: "Name cannot be blank")
- **health**: No puede ser nulo (mensaje: "Field 'health' cannot be null")
- **attack**: No puede ser nulo (mensaje: "Field 'attack' cannot be null")
- **defense**: No puede ser nulo (mensaje: "Field 'defense' cannot be null")
- **speed**: No puede ser nulo (mensaje: "Field 'speed' cannot be null")

#### Uso
- **Request (POST/PUT):** Se envía el DTO con todos los campos obligatorios (excepto `id` en POST)
- **Response:** Se devuelve el DTO completo incluyendo el `id` generado

#### Ejemplo JSON
```json
{
  "id": 1,
  "name": "Butcher",
  "health": 100,
  "attack": 10,
  "defense": 10,
  "speed": 10
}
```

---

## Arquitectura

El microservicio sigue una **arquitectura hexagonal** (puertos y adaptadores) con las siguientes capas:

- **Dominio:** Contiene el modelo `GameCharacter` y las interfaces de puertos
- **Aplicación:** Contiene la lógica de negocio en `CharacterService`
- **Infraestructura:** 
  - **Input (REST):** Controlador `GameCharacterController` y DTO `GameCharacterDto`
  - **Output (Persistencia):** Adaptadores para bases de datos

---

## Manejo de Excepciones

El microservicio incluye un manejador global de excepciones (`GlobalExceptionHandler`) que gestiona:
- `GameCharacterNotFoundException`: Cuando no se encuentra un personaje
- `DuplicateNameException`: Cuando se intenta crear un personaje con un nombre duplicado
- `EmptyListException`: Cuando la lista de personajes está vacía
- Errores de validación de campos
