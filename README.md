# User API

API REST desarrollada con **Spring Boot**, con soporte para:

- CRUD de usuarios
- Validación de datos con Jakarta Validation
- DTOs y mapeo con MapStruct
- Migraciones de base de datos con Flyway
- Configuración para entornos `dev` y `prod`
- Integración con **PostgreSQL** mediante Docker
- Manejo centralizado de errores con `ProblemDetail`
- Documentación y testeo vía Swagger UI

---

## 📦 Requisitos previos

- **Java 17+**
- **Maven**
- **Docker** y **Docker Compose**

---

## 🚀 Ejecución local (Perfil DEV)

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/nicolasscolnik/userapi.git
   cd userapi
   ```

2. Ejecutar en perfil `dev` con base de datos en memoria:
   ```bash
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
   ```

3. Acceder a Swagger UI:
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## 🐳 Ejecución en entorno **prod-like** (Docker + Postgres + Flyway)

1. Levantar servicios:
   ```bash
   docker compose up -d
   ```

2. Verificar que la base de datos está `healthy`:
   ```bash
   docker compose ps
   ```

3. Ejecutar la app en perfil `prod`:
   ```bash
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
   ```

---

## 📄 Endpoints principales

| Método | Endpoint            | Descripción           |
|--------|---------------------|-----------------------|
| GET    | `/api/users`        | Lista todos los usuarios |
| GET    | `/api/users/{id}`   | Obtiene un usuario por ID |
| POST   | `/api/users`        | Crea un nuevo usuario |
| PUT    | `/api/users/{id}`   | Actualiza un usuario existente |
| DELETE | `/api/users/{id}`   | Elimina un usuario |

---

## 🗄 Migraciones de base de datos

Las migraciones se encuentran en:

```
src/main/resources/db/migration
```

Cada cambio estructural en la base de datos debe registrarse allí como un nuevo archivo SQL.

---

## 🔒 Manejo de errores

Se usa `GlobalExceptionHandler` con `ProblemDetail` para respuestas estándar y consistentes ante errores.

---

## 📜 Licencia

Este proyecto está bajo la licencia **MIT**.
