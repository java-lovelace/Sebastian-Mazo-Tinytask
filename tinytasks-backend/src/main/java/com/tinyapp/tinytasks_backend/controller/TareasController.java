package com.tinyapp.tinytasks_backend.controller;

import com.tinyapp.tinytasks_backend.model.Tareas;
import com.tinyapp.tinytasks_backend.service.TareasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tareas")
public class TareasController {
    private final TareasService tareasService;

    public TareasController(TareasService tareasService) {
        this.tareasService = tareasService;
    }

    // Lita de todas las tareas
    @GetMapping
    public ResponseEntity<List<Tareas>> listarTareas(){
        List<Tareas> lista = tareasService.listar();
        if(lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    // para publicar una tarea
    @PostMapping
    public ResponseEntity<Tareas> crearTarea(@RequestBody Tareas nuevaTarea){
        Tareas creada = tareasService.agregarTarea(nuevaTarea);
        return ResponseEntity.ok(creada);
    }


    // endpoint para actualizar tarea
    @PutMapping("/{id}")
    public ResponseEntity<Tareas> actualizarDone (@PathVariable int id, @RequestBody Map<String, Boolean> body){
        try{
            boolean done = body.get("done");
            Tareas actualizada = tareasService.actualizar(id, done);
            return  ResponseEntity.ok(actualizada);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    // endpoint para eliminar una tarea
    @DeleteMapping("/{id}")
    public ResponseEntity<Tareas> eliminarTarea (@PathVariable int id){
        Tareas eliminada = tareasService.eliminarTarea(id);
        if (eliminada == null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(eliminada);

    }
}
