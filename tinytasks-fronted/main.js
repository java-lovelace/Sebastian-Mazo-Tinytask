import { get, post, update, deletes } from "./service/api.js";

const API_URL = "http://localhost:8080/api/tareas";

const contenedor = document.getElementById("tareas");
const form = document.getElementById("form");
const inputTitulo = document.getElementById("titulo");

async function cargarTareas(){
    const tareas = await get(API_URL);
    renderTareas(tareas);
}


// Renderizar tareas en el HTML
function renderTareas(tareas){
    contenedor.innerHTML = "";

    if(!tareas || tareas.length === 0){
        contenedor.innerHTML = "<p> No hay tareas aun </p>";
        return;
    }

    tareas.forEach(t => {
        const div = document.createElement("div");
        div.className = "tarea";
        div.innerHTML = `
        <span style="text-decoration:${t.done ? 'line-through' : 'none'}">
            ${t.title}
        </span>
        <div>
            <button onclick="marcar(${t.id}, ${t.done})">${t.done ? "Desmarcar" : "Hecho"}</button>
            <button onclick="eliminar(${t.id})">❌</button>
        </div>
        `;
        contenedor.appendChild(div);
    });
}

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const nueva = {
        title : inputTitulo.value,
        done: false
    };

    await post(API_URL, nueva);
    inputTitulo.value = "";
    cargarTareas();
});

window.eliminar = async (id) => {
    await deletes(`${API_URL}/${id}`);
    cargarTareas();
};
window.marcar = async (id, done) => {
    await update(`${API_URL}/${id}`, { done: !done });
    cargarTareas();
};

cargarTareas();