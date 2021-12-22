package fr.fab.test.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.fab.test.dto.UserDto;
import fr.fab.test.services.UserSvc;
import lombok.extern.log4j.Log4j2;



@RestController
@RequestMapping(value = "/user-management", produces = {MediaType.APPLICATION_JSON_VALUE})
@Log4j2
public class UserController {

    @Autowired
    private UserSvc userSvc;
    

    @GetMapping(value="/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        if (log.isDebugEnabled()){
            log.debug("recuperation de la liste des users");
        }
       try {
           return new ResponseEntity<>(userSvc.list(), HttpStatus.OK);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
    }
    @GetMapping(value="/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        if (log.isDebugEnabled()){
            log.debug(String.format("recuperation du user %n", id));
        }
        return new ResponseEntity<>(userSvc.byId(id), HttpStatus.OK);
        //traiter les differents cas comme indiqué ici
        //https://www.restapitutorial.com/lessons/httpmethods.html
    
    }
    

    @PostMapping(value="/user")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        try {
            return new ResponseEntity<>(userSvc.save(userDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    
}
