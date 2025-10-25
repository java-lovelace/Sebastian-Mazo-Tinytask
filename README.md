# TinyTasks
Complete task management system with Spring Boot REST API backend and vanilla JavaScript frontend. CRUD operations with modern UI and layered architecture.

## Overview

Full-stack application for managing tasks with a RESTful backend and interactive frontend. Features include creating, updating, marking as complete, and deleting tasks with real-time synchronization.

## Technologies

**Backend:**
- Java 17
- Spring Boot 3.5.6
- Spring Web
- Maven
- REST API

**Frontend:**
- HTML5
- CSS3
- JavaScript ES6 (Modules)
- Fetch API

## Project Structure

    tinytasks/
    ├── tinytasks-backend/
    │   ├── src/main/java/com/tinyapp/tinytasks_backend/
    │   │   ├── TinytasksBackendApplication.java
    │   │   ├── config/
    │   │   │   └── CorsConfig.java
    │   │   ├── controller/
    │   │   │   └── TareasController.java
    │   │   ├── service/
    │   │   │   └── TareasService.java
    │   │   ├── repository/
    │   │   │   └── TareasRepository.java
    │   │   └── model/
    │   │       └── Tareas.java
    │   ├── pom.xml
    │   └── README.md
    └── tinytasks-frontend/
        ├── index.html
        ├── main.js
        ├── service/
        │   └── api.js
        └── README.md

## Architecture

    Frontend (HTML/JS)
           ↓
      Fetch API
           ↓
    REST Controller
           ↓
       Service
           ↓
      Repository
           ↓
    In-Memory Storage

## Installation & Setup

### Prerequisites

- Java JDK 17+
- Maven 3.6+
- Modern web browser
- Live Server (VS Code extension) or similar

### Backend Setup

    cd tinytasks-backend
    mvn clean install
    mvn spring-boot:run

Backend runs on: `http://localhost:8080`

### Frontend Setup

    cd tinytasks-frontend
    # Open with Live Server on port 5500
    # Or use any local server

Frontend runs on: `http://localhost:5500` (default Live Server port)

## API Documentation

### Base URL

    http://localhost:8080/api/tareas

### Endpoints

**Get All Tasks**

    GET /api/tareas

Response (200):

    [
        {
            "id": 1,
            "title": "Complete project",
            "done": false
        }
    ]

**Create Task**

    POST /api/tareas
    Content-Type: application/json

Body:

    {
        "title": "New task"
    }

Response (200):

    {
        "id": 2,
        "title": "New task",
        "done": false
    }

**Update Task**

    PUT /api/tareas/{id}
    Content-Type: application/json

Body:

    {
        "done": true
    }

**Delete Task**

    DELETE /api/tareas/{id}

Response (200): Deleted task object

## Features

### Backend Features

- RESTful API with Spring Boot
- Layered architecture (Controller → Service → Repository)
- CORS configuration for cross-origin requests
- Input validation
- In-memory storage with auto-increment IDs
- Exception handling
- HTTP status codes (200, 204, 404)

### Frontend Features

- Clean and modern UI design
- Real-time task updates
- Add new tasks
- Mark tasks as done/undone
- Delete tasks
- Modular JavaScript (ES6 modules)
- Responsive design
- Visual feedback (hover effects, transitions)

## Data Model

    {
        "id": Long,
        "title": String,
        "done": boolean
    }

| Field | Type    | Description              |
|-------|---------|--------------------------|
| id    | Long    | Auto-generated unique ID |
| title | String  | Task description         |
| done  | boolean | Completion status        |

## CORS Configuration

Allowed origins:
- `http://localhost:5500` (Live Server)
- `http://127.0.0.1:5500`
- `http://localhost:3000` (React/Next.js)

Allowed methods: GET, POST, PUT, DELETE, OPTIONS

## Usage

### Adding a Task

1. Type task title in the input field
2. Click "Agregar" button
3. Task appears in the list below

### Marking as Done

1. Click "Hecho" button on any task
2. Task gets strikethrough styling
3. Button changes to "Desmarcar"

### Deleting a Task

1. Click the ❌ button
2. Task is removed from the list

## Frontend API Service

Located in `service/api.js`:

    export async function get(url)           - Fetch data
    export async function post(url, body)    - Create resource
    export async function update(url, body)  - Update resource
    export async function deletes(url)       - Delete resource

## Code Examples

### Backend - Creating a Task

    @PostMapping
    public ResponseEntity<Tareas> crearTarea(@RequestBody Tareas nuevaTarea){
        Tareas creada = tareasService.agregarTarea(nuevaTarea);
        return ResponseEntity.ok(creada);
    }

### Frontend - Fetching Tasks

    async function cargarTareas(){
        const tareas = await get(API_URL);
        renderTareas(tareas);
    }

### Frontend - Creating a Task

    const nueva = {
        title: inputTitulo.value,
        done: false
    };
    await post(API_URL, nueva);
    cargarTareas();

## Testing

### Backend Tests

Run with Maven:

    mvn test

### Manual API Testing

Using cURL:

    # Get all tasks
    curl http://localhost:8080/api/tareas

    # Create task
    curl -X POST http://localhost:8080/api/tareas \
      -H "Content-Type: application/json" \
      -d '{"title":"Test task"}'

    # Update task
    curl -X PUT http://localhost:8080/api/tareas/1 \
      -H "Content-Type: application/json" \
      -d '{"done":true}'

    # Delete task
    curl -X DELETE http://localhost:8080/api/tareas/1

## Troubleshooting

**CORS Error:**
- Check backend CORS configuration in `CorsConfig.java`
- Ensure frontend origin is listed in `allowedOrigins`

**Connection Refused:**
- Verify backend is running on port 8080
- Check `API_URL` in `main.js` matches backend URL

**Tasks Not Appearing:**
- Open browser console for errors
- Verify API responses in Network tab
- Check backend console for errors

## Future Improvements

- Persistent database (MySQL/PostgreSQL)
- User authentication
- Task categories/tags
- Due dates and reminders
- Task priority levels
- Search and filter functionality
- Pagination for large lists
- Dark mode toggle
- Mobile responsive design
- Drag and drop reordering
- Task editing functionality
- Statistics dashboard

## HTTP Status Codes

| Code | Meaning                |
|------|------------------------|
| 200  | OK - Success           |
| 204  | No Content - Empty list|
| 404  | Not Found - Invalid ID |
| 400  | Bad Request - Invalid  |

## Developer Information

**Name:** SebitasDown
**GitHub:** @SebitasDown
**Project:** TinyTasks Full Stack Application

## License

Educational project - Open source for learning

---

Built with Java Spring Boot and Vanilla JavaScript
