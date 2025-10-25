# TinyTasks 

Complete task management application with Spring Boot backend and vanilla JavaScript frontend.

## Project Structure

    tinytasks/
    ├── tinytasks-backend/     (Spring Boot API)
    └── tinytasks-frontend/    (Vanilla JS UI)

## Technologies

**Backend:** Java 17 | Spring Boot 3.5.6 | Maven
**Frontend:** HTML5 | CSS3 | JavaScript ES6 | Fetch API

## Quick Start

### Backend

    cd tinytasks-backend
    mvn spring-boot:run

Runs on: `http://localhost:8080`

### Frontend

Open `tinytasks-frontend/index.html` with Live Server

## API Endpoints

    GET    /api/tareas       - List all tasks
    POST   /api/tareas       - Create task
    PUT    /api/tareas/{id}  - Update task
    DELETE /api/tareas/{id}  - Delete task

## Features

- Create, read, update, delete tasks
- Mark tasks as done/undone
- Clean modern UI
- CORS configured
- In-memory storage

## Data Model

    {
        "id": Long,
        "title": String,
        "done": boolean
    }

---

Built with Spring Boot and Vanilla JavaScript
