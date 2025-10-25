# TinyTasks Backend - API REST de Gestión de Tareas

API REST desarrollada con Spring Boot para gestionar tareas (TODO list) con operaciones CRUD completas y almacenamiento en memoria.

## Descripción

Backend de aplicación de lista de tareas que proporciona endpoints RESTful para crear, listar, actualizar y eliminar tareas. Implementa arquitectura en capas siguiendo las mejores prácticas de Spring Boot.

## Tecnologías

Java 17+ | Spring Boot | Spring Web | Maven | REST API

## Estructura del Proyecto

    src/main/java/com/tinyapp/tinytasks_backend/
    ├── TinytasksBackendApplication.java (Clase principal)
    ├── config/
    │   └── CorsConfig.java (Configuración CORS)
    ├── controller/
    │   └── TareasController.java (Endpoints REST)
    ├── service/
    │   └── TareasService.java (Lógica de negocio)
    ├── repository/
    │   └── TareasRepository.java (Acceso a datos)
    └── model/
        └── Tareas.java (Modelo de datos)

## Arquitectura en Capas

    Cliente (Frontend)
           ↓
    Controller (REST API)
           ↓
    Service (Lógica de negocio)
           ↓
    Repository (Almacenamiento)
           ↓
    Memoria (Lista in-memory)

## Instalación y Ejecución

### Requisitos Previos

- Java JDK 17 o superior
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Clonar el repositorio

    git clone <url-del-repositorio>
    cd tinytasks-backend

### Compilar y ejecutar

    mvn clean install
    mvn spring-boot:run

La aplicación se ejecutará en: `http://localhost:8080`

## Endpoints de la API

### Base URL

    http://localhost:8080/api/tareas

### Listar todas las tareas

    GET /api/tareas

Respuesta exitosa (200):

    [
        {
            "id": 1,
            "title": "Completar proyecto",
            "done": false
        },
        {
            "id": 2,
            "title": "Estudiar Spring Boot",
            "done": true
        }
    ]

Respuesta sin contenido (204): Lista vacía

### Crear nueva tarea

    POST /api/tareas
    Content-Type: application/json

Body:

    {
        "title": "Nueva tarea"
    }

Respuesta (200):

    {
        "id": 3,
        "title": "Nueva tarea",
        "done": false
    }

### Actualizar estado de tarea

    PUT /api/tareas/{id}
    Content-Type: application/json

Body:

    {
        "done": true
    }

Respuesta (200):

    {
        "id": 1,
        "title": "Completar proyecto",
        "done": true
    }

Error (404): Tarea no encontrada

### Eliminar tarea

    DELETE /api/tareas/{id}

Respuesta (200):

    {
        "id": 1,
        "title": "Completar proyecto",
        "done": false
    }

Error (404): Tarea no encontrada

## Modelo de Datos

### Tareas

    {
        "id": Long,
        "title": String,
        "done": boolean
    }

| Campo | Tipo    | Descripción                  |
|-------|---------|------------------------------|
| id    | Long    | Identificador único          |
| title | String  | Título de la tarea           |
| done  | boolean | Estado (completada o no)     |

## Configuración CORS

La API permite peticiones desde:

- `http://localhost:5500` (Live Server)
- `http://127.0.0.1:5500`
- `http://localhost:3000` (React/Next.js)

Métodos permitidos: GET, POST, PUT, DELETE, OPTIONS

Para modificar orígenes permitidos, edita `CorsConfig.java`:

    .allowedOrigins(
        "http://localhost:5500",
        "http://tu-nuevo-origen"
    )

## Características Principales

### Validaciones

- Título no puede estar vacío
- ID debe existir para actualizar/eliminar
- Estado done por defecto en false al crear

### Manejo de Errores

- 404 Not Found: Tarea no encontrada
- 204 No Content: Lista vacía
- 400 Bad Request: Título vacío

### Almacenamiento

- In-Memory Storage (ArrayList)
- Auto-incremento de IDs
- Datos se pierden al reiniciar la aplicación

## Flujo de Operaciones

### Crear Tarea

    Cliente → POST /api/tareas
         ↓
    TareasController.crearTarea()
         ↓
    TareasService.agregarTarea()
         - Valida título no vacío
         - Establece done = false
         ↓
    TareasRepository.agregar()
         - Asigna ID automático
         - Agrega a lista
         ↓
    Retorna tarea creada

### Actualizar Tarea

    Cliente → PUT /api/tareas/{id}
         ↓
    TareasController.actualizarDone()
         ↓
    TareasService.actualizar()
         ↓
    TareasRepository.actualizarTarea()
         - Busca tarea por ID
         - Actualiza campo done
         ↓
    Retorna tarea actualizada

### Eliminar Tarea

    Cliente → DELETE /api/tareas/{id}
         ↓
    TareasController.eliminarTarea()
         ↓
    TareasService.eliminarTarea()
         ↓
    TareasRepository.eliminarTarea()
         - Busca tarea por ID
         - Elimina de lista
         ↓
    Retorna tarea eliminada

## Códigos de Estado HTTP

| Código | Descripción                    |
|--------|--------------------------------|
| 200    | OK - Operación exitosa         |
| 204    | No Content - Lista vacía       |
| 404    | Not Found - Recurso no existe  |
| 400    | Bad Request - Datos inválidos  |

## Pruebas con cURL

### Crear tarea

    curl -X POST http://localhost:8080/api/tareas \
      -H "Content-Type: application/json" \
      -d '{"title":"Mi primera tarea"}'

### Listar tareas

    curl http://localhost:8080/api/tareas

### Actualizar tarea

    curl -X PUT http://localhost:8080/api/tareas/1 \
      -H "Content-Type: application/json" \
      -d '{"done":true}'

### Eliminar tarea

    curl -X DELETE http://localhost:8080/api/tareas/1

## Pruebas con Postman

1. Importa la colección de endpoints
2. Configura Base URL: `http://localhost:8080`
3. Prueba cada endpoint con diferentes datos

## Mejoras Futuras

- Persistencia con base de datos (MySQL/PostgreSQL)
- Autenticación y autorización (JWT)
- Paginación de resultados
- Filtros por estado (done/pending)
- Búsqueda por título
- Fechas de creación y vencimiento
- Prioridades para tareas
- Categorías o etiquetas
- Pruebas unitarias con JUnit
- Documentación con Swagger/OpenAPI
- Logging con SLF4J
- Manejo de excepciones personalizado
- Validación con Bean Validation
- Docker containerization

## Integración Frontend

Ejemplo con JavaScript/Fetch:

    // Obtener todas las tareas
    fetch('http://localhost:8080/api/tareas')
        .then(res => res.json())
        .then(data => console.log(data));

    // Crear tarea
    fetch('http://localhost:8080/api/tareas', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title: 'Nueva tarea' })
    })
    .then(res => res.json())
    .then(data => console.log(data));

    // Actualizar tarea
    fetch('http://localhost:8080/api/tareas/1', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ done: true })
    })
    .then(res => res.json())
    .then(data => console.log(data));

    // Eliminar tarea
    fetch('http://localhost:8080/api/tareas/1', {
        method: 'DELETE'
    })
    .then(res => res.json())
    .then(data => console.log(data));

## Conceptos Aplicados

- REST API principles
- Spring Boot annotations (@RestController, @Service, @Repository)
- Dependency Injection
- Arquitectura en capas (MVC)
- HTTP methods (GET, POST, PUT, DELETE)
- ResponseEntity para respuestas HTTP
- CORS configuration
- Exception handling
- In-memory storage

## Información del Desarrollador

Nombre: SebitasDown
GitHub: @SebitasDown
Proyecto: TinyTasks Backend API

## Licencia

Proyecto educativo - Código abierto para aprendizaje

---

Desarrollado con Java y Spring Boot
