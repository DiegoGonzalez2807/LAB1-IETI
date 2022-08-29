package edu.escuelaing.ieti.appTasks.controller;

/**
 * @author Diego Gonzalez
 */
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.ieti.appTasks.entities.Task;
import edu.escuelaing.ieti.appTasks.persistence.TaskServicePersistenceException;
import edu.escuelaing.ieti.appTasks.service.TaskService;

@RestController
public class TaskController {
    //Conexion con TaskService
    private final TaskService taskService;

    public TaskController(@Autowired TaskService taskService){
        this.taskService = taskService;
    }

    /**
     * Funcion generada para retornar todas las tareas que el usuario
     * haya guardado 
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(){
        try{
            return new ResponseEntity<>(taskService.getAll(),HttpStatus.ACCEPTED);
        }
        catch(TaskServicePersistenceException ex){
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.FORBIDDEN);        }
    }

    /**
     * Funcion generada para retornar una tarea específica creada por el usuario.
     * Esta se bsuca de acuerdo con su nombre   
     */
    @GetMapping("/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        try{
            return new ResponseEntity<>(taskService.findById(name),HttpStatus.ACCEPTED);
        }
        catch(TaskServicePersistenceException ex){
            ex.printStackTrace();
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Funcion generada para crear una tarea
     * @param task
     * @return
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        try{
            return new ResponseEntity<>(taskService.create(task),HttpStatus.ACCEPTED);
        }
        catch(TaskServicePersistenceException ex){
            ex.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Funcion generada para actualizar una tarea guardada con los nuevos valores
     * dados por el usuario
     * @param task
     * @param name
     * @return
     */
    @PutMapping("/{name}")
    public ResponseEntity<Task> updateTask( @RequestBody Task task, @PathVariable String name){
        try{
            return new ResponseEntity<>(taskService.update(task, name),HttpStatus.ACCEPTED);
        }
        catch(TaskServicePersistenceException ex){
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Funcion generada para eliminar una tarea específica que esté guardada
     * @param name
     * @return
     */
    @DeleteMapping("/{name}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable String name){
        try{
            taskService.deleteById(name);
            return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
        }
        catch(TaskServicePersistenceException ex){
            ex.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }
    }
}
