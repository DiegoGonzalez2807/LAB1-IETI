
# Goals

 - Explain what needs to be done to achieve the Level 2 on a RESTFUL API
   on the Richardson Maturity Model.
 - Implement a Level 2 Users RESTFUL API Microservice.
 - Implement a Level 2 Tasks RESTFUL API Microservice.
 - User dependencies injections to create a decoupled architecture.

# MaIn Topics

 - Microservices.
 - RESTFUL API.
 - Richardson Maturity Model.
 - Dependencies Injection.

# Codelab 🤹🏽
## Part 1: Implementing the Users Microservice RESTFUL API

 1. Create a new project using the Spring Initializr .
 2. Use either **Java** or **Kotlin** as programming language.
 3. Use **Gradle** as project option.
 4. Add Spring Web dependency.
 5. Use latest Spring Boot version (make sure your selection doesn't
    contain SNAPSHOT or M1 (those are not as stable as the rest).
 6. Click on generate.
 8. Create a new public repo, name it as you like.
 9. Commit/push the files generated.
 10. Open the project on favorite editor (IntelliJ IDEA is highly
     recommended throughout this course).
 11. Create a new package called ***dto*** and inside define your ***UserDto***
     object with at least the following fields:

       - id
       - name
       - email
       - lastName
       - createdAt

12. Create a new package called ***entities*** and inside define your ***User*** entity object with the same fields as the dto version (Types don't matter yet, just be sure both DTO classes and Data classes types are consistent enough): 

	
13. Create a new package called ***service*** and inside create the following interface:

    ```java
     public interface UserService
     {
         User create( User user );

         User findById( String id );
         
         List<User> getAll();

         void deleteById( String id );

         User update( User user, String userId );
     }  
14. Create an implementation of the ***UserService*** using a HashMap data structure inside.
15. Make your service implementation ***UserServiceHashMap*** injectable using the ***@Service*** annotation.
16. Create a new package called ***controller*** and create a new class ***UserController*** inside.
17. Annotate your ***UserController*** so it becomes a ***REST*** Controller:
18. Inject your ***UserService*** implementation inside the ***UserController*** via the constructor:
19.  Implement all the endpoints needed to interact with you  _UserService_. Use the following method signatures to help you achieve the Level 2 RESTFUL Maturity:
   ```java
    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        //TODO implement this method using UserService
        return null;
    }
   
    @GetMapping( "/{id}" )
    public ResponseEntity<UserDto> findById( @PathVariable String id ) {
       //TODO implement this method using UserService
       return null;
    }
   
   
    @PostMapping
    public ResponseEntity<UserDto> create( @RequestBody UserDto userDto ) {
         //TODO implement this method using UserService
         return null;
    }
   
    @PutMapping( "/{id}" )
    public ResponseEntity<UserDto> update( @RequestBody UserDto user, @PathVariable String id ) {
         //TODO implement this method using UserService
         return null;
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Boolean> delete( @PathVariable String id ) {
         //TODO implement this method using UserService
        return null;      
    }
  }
 ```
20. Remember to only expose to client relevant fields through Dto classes, not Entity classes (to decouple persistence model from API), you should implement on both User and UserDto mapping methods (toEntity, toDto accordingly).
20. Build / Run your project.
21. Download and install [Insomnia](https://insomnia.rest/download) and test ALL the endpoints of your API.

### Pruebas REST para el Usuario
### Insercion de Usuario
![](https://github.com/DiegoGonzalez2807/LAB1-IETI/blob/master/Primera%20Parte/src/main/resources/images/Insercion2.png)

### Traer todos los usuarios (Contexto: Se crea otro usuario para ver de mejor manera la correcta ejecución)
![](https://github.com/DiegoGonzalez2807/LAB1-IETI/blob/master/Primera%20Parte/src/main/resources/images/GetAll2.png)

### Encontrar usuario por identificador (ID)
![](https://github.com/DiegoGonzalez2807/LAB1-IETI/blob/master/Primera%20Parte/src/main/resources/images/Find1.png)

### Actualización de usuario
![](https://github.com/DiegoGonzalez2807/LAB1-IETI/blob/master/Primera%20Parte/src/main/resources/images/Update1.png)
#### Se realiza una búsqueda de todos los usuarios para ver si se actualizó de manera correcta el usuario
![](https://github.com/DiegoGonzalez2807/LAB1-IETI/blob/master/Primera%20Parte/src/main/resources/images/Update2.png)

### Eliminación de usuario
![](https://github.com/DiegoGonzalez2807/LAB1-IETI/blob/master/Primera%20Parte/src/main/resources/images/Delete1.png)
#### Se realiza una búsqueda de todos los usuarios para ver si se eliminó de manera correcta el usuario
![](https://github.com/DiegoGonzalez2807/LAB1-IETI/blob/master/Primera%20Parte/src/main/resources/images/Delete2.png)


## Part 2: Implementing the Tasks Microservice RESTFUL API

1. Follow the previous part steps
2. Create a new package called ***dto*** and inside define your ***TaskDto*** object with at least the following fields:
        - id
	- name
	- description
	- status ( TODO, DOING, REVIEW and DONE )
	- assignedTo
	- dueDate
	- createdAt
3. Create a new package called ***entities*** and inside define your ***Task*** entity object with the same fields as the dto version:
4. Create a new package called ***service*** and inside create the following interface:
	```java
	public interface TaskService
    {
        Task create( Task task );

        Task findById( String id );
        
        List<Task> getAll();

        boolean deleteById( String id );

        Task update( Task task, String id );
    }
    ```
5. Create an implementation of the ***TaskService*** using a ***HashMap*** data structure inside.
6. Make sure your service implementation ***TaskServiceHashMap*** is injectable using the ***@Service*** annotation.
7. Implement your ***TaskController*** (Try to avoid copy paste, use the **User Microservice** as a reference but try doing it conciously).
8. Test ALL the endpoints of your API using **Insomnia**.

## 
### Pruebas REST para las tareas
### Insercion de tarea
![](https://github.com/DiegoGonzalez2807/LAB1-IETI/blob/master/Segunda%20Parte/src/main/resources/images/Insercion1.png)

### Traer todas las tareas (Contexto: Se crea otra tarea para ver de mejor manera la correcta ejecución)
![](https://github.com/DiegoGonzalez2807/LAB1-IETI/blob/master/Segunda%20Parte/src/main/resources/images/TodoValores1.png)

### Encontrar tarea por nombre 
![](https://github.com/DiegoGonzalez2807/LAB1-IETI/blob/master/Segunda%20Parte/src/main/resources/images/PorNombre1.png)

### Actualización de tarea
![](https://github.com/DiegoGonzalez2807/LAB1-IETI/blob/master/Segunda%20Parte/src/main/resources/images/Modificar1.png)
#### Se realiza una búsqueda de todas las tareas para ver si se actualizó de manera correcta
![](https://github.com/DiegoGonzalez2807/LAB1-IETI/blob/master/Segunda%20Parte/src/main/resources/images/Modificar2.png)

### Eliminación de tarea
![](https://github.com/DiegoGonzalez2807/LAB1-IETI/blob/master/Segunda%20Parte/src/main/resources/images/Eliminar1.png)
#### Se realiza una búsqueda de todas las tareas para ver si se eliminó de manera correcta 
![](https://github.com/DiegoGonzalez2807/LAB1-IETI/blob/master/Segunda%20Parte/src/main/resources/images/Eliminar2.png)

Remember to add your both **Github** repos url into the **Ada** input box to deliver correctly your codelab.
