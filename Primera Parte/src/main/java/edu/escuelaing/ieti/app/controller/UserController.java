package edu.escuelaing.ieti.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.escuelaing.ieti.app.entities.User;
import edu.escuelaing.ieti.app.persistence.UserServicePersistenceException;
import edu.escuelaing.ieti.app.service.UserService;

@RestController
public class UserController {
    //Conexion con userService
    private final UserService userService;

    /**
     * Constructor generado para dar valor a la variable userService
     * @param userService
     */
    public UserController(@Autowired UserService userService){
        this.userService = userService;
    }

    /**
     * Funcion generada para retornar la lista de usuarios que se tenga guardada
     * @return -> List<User>
     */
    @GetMapping
 public ResponseEntity<List<User>> getAll() {
     try{
        return new ResponseEntity<>(userService.getAll(), HttpStatus.ACCEPTED);
     }
     catch(UserServicePersistenceException ex){
        ex.printStackTrace();
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.FORBIDDEN);
     }
 }

 /**
  * Funcion generada para retornar el usuario que requiera el cliente de acuerdo
  con el identificador
  * @param id
  * @return
  */
 @GetMapping( "/{id}" )
 public ResponseEntity<User> findById( @PathVariable String id ) {
    try{
        return new ResponseEntity<>(userService.findById(id),HttpStatus.ACCEPTED);
    }
    catch(UserServicePersistenceException ex){
        ex.printStackTrace();
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
 }

/**
 * Funcion generada para crear un usuario de acuerdo con los parametros
 * dados por el cliente 
 * @param user
 * @return
 */
 @PostMapping
 public ResponseEntity<User> create( @RequestBody User user ) {
    try{
        return new ResponseEntity<>(userService.create(user), HttpStatus.ACCEPTED);
    }
    catch(UserServicePersistenceException ex){
        return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
    }
 }

 /**
  * Funcion generada para actualizar los datos de un usuario de acuerdon con su id
  * @param user
  * @param id
  * @return
  */
 @PutMapping( "/{id}" )
 public ResponseEntity<User> update( @RequestBody User user, @PathVariable String id ) {
      try{
        return new ResponseEntity<>(userService.update(user,id), HttpStatus.ACCEPTED);
      }
      catch(UserServicePersistenceException ex){
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
      }
 }

 /**
  * Funcion generada para eliminar a un usuario de acuerdo con su identificador
  * @param id
  * @return
  */
 @DeleteMapping( "/{id}" )
 public ResponseEntity<Boolean> delete( @PathVariable String id ) {
    try{
        userService.deleteById(id);
        return new ResponseEntity<>(true,HttpStatus.ACCEPTED);
    }
    catch(UserServicePersistenceException ex){
        return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
    }
 }
}