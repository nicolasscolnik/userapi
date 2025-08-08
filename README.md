
# 📌 User API - Spring Boot 3 + Swagger

Este proyecto es una API REST sencilla para la gestión de usuarios, desarrollada con **Spring Boot 3**, **JPA**, **H2** y documentada con **Swagger (OpenAPI)**.

## 🚀 Características

- CRUD completo para usuarios.
- Validación de datos con `jakarta.validation`.
- Persistencia con **Spring Data JPA** y **H2 en memoria**.
- Documentación automática con **springdoc-openapi**.
- Manejo global de errores de validación.

## 📦 Requisitos

- Java 17+
- Maven 3.8+

## ⚙️ Instalación

```bash
# Clonar el repositorio
git clone https://github.com/nicolasscolnik/userapi.git
cd userapi

# Compilar e instalar dependencias
./mvnw clean install

# Ejecutar la aplicación
./mvnw spring-boot:run
```

La API estará disponible en:

```
http://localhost:8080
```

## 📚 Documentación Swagger

Una vez iniciada la aplicación, podés acceder a:

- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Esquema OpenAPI:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## 📂 Endpoints principales

| Método | Endpoint         | Descripción                       |
|--------|-----------------|-----------------------------------|
| GET    | `/api/users`    | Lista todos los usuarios          |
| GET    | `/api/users/{id}` | Obtiene un usuario por ID         |
| POST   | `/api/users`    | Crea un nuevo usuario              |
| PUT    | `/api/users/{id}` | Actualiza un usuario existente    |
| DELETE | `/api/users/{id}` | Elimina un usuario                |

## 🗄 Ejemplo de JSON para crear un usuario

```json
{
  "name": "Juan Pérez",
  "email": "juan@mail.com"
}
```

## 🛠 Tecnologías utilizadas

- **Spring Boot 3**
- **Spring Data JPA**
- **H2 Database**
- **Springdoc OpenAPI**
- **Jakarta Validation**
- **Maven**

## 📄 Licencia

Este proyecto se distribuye bajo la licencia MIT.
