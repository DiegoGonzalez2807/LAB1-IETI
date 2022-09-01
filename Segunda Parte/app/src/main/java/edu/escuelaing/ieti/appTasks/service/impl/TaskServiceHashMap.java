package edu.escuelaing.ieti.appTasks.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import edu.escuelaing.ieti.appTasks.entities.Task;
import edu.escuelaing.ieti.appTasks.persistence.TaskServicePersistenceException;
import edu.escuelaing.ieti.appTasks.service.TaskService;

@Service
public class TaskServiceHashMap implements TaskService{

    //Estructura de Hashmap para tareas
    private ConcurrentHashMap<String,Task> tasks = new ConcurrentHashMap<>();

    /**
     * Funcion generada para crear una tarea que quiera guardar el usuario
     * en el hashmap. Se guarda como llave el nombre de la tarea
     */
    @Override
    public Task create(Task task) throws TaskServicePersistenceException {
        if(tasks.keySet().contains(task.getName())){
            throw new TaskServicePersistenceException(TaskServicePersistenceException.PROHIBIDO);
        }
        tasks.put(task.getName(), task);
        return task;
    }

    /**
     * Funcion generada para encontrar una tarea de acuerdo con su nombre
     */
    @Override
    public Task findById(String id) throws TaskServicePersistenceException{
        if(!tasks.keySet().contains(id)){
            throw new TaskServicePersistenceException(TaskServicePersistenceException.EXCEPTION_NO_ENCONTRADO);
        }
        return tasks.get(id);
    }

    /**
     * Funcion generada para retornar todas las tareas que el usuario 
     * haya guardado en el arreglo hashmap
     */
    @Override
    public List<Task> getAll() {
        List<Task> allTasks = new ArrayList<Task>();
        for(String name: tasks.keySet()){
            allTasks.add(tasks.get(name));
        }
        return allTasks;
    }

    /**
     * @throws TaskServicePersistenceException
     * Funcion generada para eliminar una tarea de acuerdo con su nombre
     */
    @Override
    public boolean deleteById(String id) throws TaskServicePersistenceException {
        if(tasks.keySet().contains(id)){
            tasks.remove(id);
            return true;
        }
        else{
            throw new TaskServicePersistenceException(TaskServicePersistenceException.PROHIBIDO);
        }
    }

    /**
     * Funcion generada para actualizar los datos de una tarea.
     * Esta se busca de acuerdo a su nombre
     * @throws TaskServicePersistenceException
     */
    @Override
    public Task update(Task task, String id) throws TaskServicePersistenceException {
        if(!tasks.keySet().contains(id)){
            throw new TaskServicePersistenceException(TaskServicePersistenceException.EXCEPTION_NO_ENCONTRADO);
        }
        return tasks.replace(id,task);
    }
    
}
