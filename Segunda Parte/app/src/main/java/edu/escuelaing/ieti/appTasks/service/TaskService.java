package edu.escuelaing.ieti.appTasks.service;

import java.util.List;
import edu.escuelaing.ieti.appTasks.entities.Task;
import edu.escuelaing.ieti.appTasks.persistence.TaskServicePersistenceException;
/**
 * @author Diego Gonzalez
 */

public interface TaskService
{
    Task create( Task task ) throws TaskServicePersistenceException;

    Task findById( String id ) throws TaskServicePersistenceException;
    
    List<Task> getAll() throws TaskServicePersistenceException;

    boolean deleteById( String id ) throws TaskServicePersistenceException;

    Task update( Task task, String id ) throws TaskServicePersistenceException;
}
