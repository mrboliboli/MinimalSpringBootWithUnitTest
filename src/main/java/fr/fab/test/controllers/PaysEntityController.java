package fr.fab.test.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.fab.test.models.Pays;
import fr.fab.test.services.PaysSvc;


@RestController
@RequestMapping(value = "pays-entity")
public class PaysEntityController {

    @Autowired
    private PaysSvc paysSvc;

    @GetMapping(value="/countries")
    public ResponseEntity<List<Pays>> getAll() {
        return new ResponseEntity<>(paysSvc.entityList(), HttpStatus.OK);
    }
    
    
}
