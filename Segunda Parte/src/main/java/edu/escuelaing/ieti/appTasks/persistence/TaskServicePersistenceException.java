package edu.escuelaing.ieti.appTasks.persistence;

public class TaskServicePersistenceException extends Exception{
    public static final String EXCEPTION_NO_ENCONTRADO = "El elemento que estas buscando no esta disponible";
    public static final String PROHIBIDO ="La transaccion que quieres hacer no esta permitida";

    public TaskServicePersistenceException(String message){
        super(message);
    }

    public TaskServicePersistenceException(){
        super();
    }

    public TaskServicePersistenceException(String message, Throwable cause){
        super(message,cause);
    }

}
