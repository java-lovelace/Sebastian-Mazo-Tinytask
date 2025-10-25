package com.tinyapp.tinytasks_backend.repository;

import com.tinyapp.tinytasks_backend.model.Tareas;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TareasRepository {
    // Aqui se guardaran todas las tareas
    private final List<Tareas> listadeTareas = new ArrayList<>();
    private int nextId = 1;

    // Recibe las tareas de service y las guarda con el id correspondiente
    public Tareas agregar(Tareas tarea){
        tarea.setId((long) nextId ++);
        listadeTareas.add(tarea);
        return tarea;
    }

    // Lista de todas las tareas
    public List<Tareas> obtenerTodas(){
        return listadeTareas;
    }


    // Metodo para buscar el Id
    public Tareas buscarporId (int id){
        for (Tareas tarea : listadeTareas){
            if(tarea.getId() == id){
                return tarea;
            }
        }
        System.out.println("No se encontro la tarea");
        return null;

    }

    // Actualizar el estado donde a true
    public Tareas actualizarTarea (int id, boolean done){

        Tareas tarea = buscarporId(id);
        if(tarea != null){
            tarea.setDone(done);
            return tarea;
        }
        return null;

    }

    // Eliminar tarea en especifico
    public Tareas eliminarTarea (int id){
        Tareas tarea = buscarporId(id);
        if(tarea !=null){
            listadeTareas.remove(tarea);
            return tarea;
        }
        return null;
    }

}
