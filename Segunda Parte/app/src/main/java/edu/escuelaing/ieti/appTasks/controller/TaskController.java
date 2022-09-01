package edu.escuelaing.ieti.appTasks.controller;

/**
 * @author Diego Gonzalez
 */
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.escuelaing.ieti.appTasks.dto.TaskDto;
import edu.escuelaing.ieti.appTasks.entities.Task;
import edu.escuelaing.ieti.appTasks.persistence.TaskServicePersistenceException;
import edu.escuelaing.ieti.appTasks.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    //Conexion con TaskService
    private final TaskService taskService;

    private ModelMapper modelMapper = new ModelMapper();

    public TaskController(@Autowired TaskService taskService){
        this.taskService = taskService;
    }

    /**
     * Funcion generada para retornar todas las tareas que el usuario
     * haya guardado 
     * @return
     */
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        try{
            List<Task> tasks = taskService.getAll();
            List<TaskDto> tasksConverted = new ArrayList<TaskDto>();
            for(Task task : tasks){
                tasksConverted.add(modelMapper.map(task, TaskDto.class));
            }
            return new ResponseEntity<>(tasksConverted,HttpStatus.ACCEPTED);
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
    public ResponseEntity<TaskDto> findByName(@PathVariable String name){
        try{
            return new ResponseEntity<>(modelMapper.map(taskService.findById(name), TaskDto.class),HttpStatus.ACCEPTED);
        }
        catch(TaskServicePersistenceException ex){
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Funcion generada para crear una tarea
     * @param task
     * @return
     */
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody Task task){
        try{
            return new ResponseEntity<>(modelMapper.map(taskService.create(task), TaskDto.class),HttpStatus.ACCEPTED);
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
    public ResponseEntity<TaskDto> updateTask( @RequestBody Task task, @PathVariable String name){
        try{
            return new ResponseEntity<>(modelMapper.map(taskService.update(task, name), TaskDto.class),HttpStatus.ACCEPTED);
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
