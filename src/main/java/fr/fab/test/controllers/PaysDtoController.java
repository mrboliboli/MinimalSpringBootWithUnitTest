package fr.fab.test.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.fab.test.dto.PaysDto;
import fr.fab.test.services.PaysSvc;

/**
 * Controlleur REST pour les pays
 * Pas besoin de preciser les mediaTypes par defaut spring place le mediatype sur 'application/json'
 */
@RestController
@RequestMapping(value = "/pays-dto")
public class PaysDtoController {

    @Autowired
    private  PaysSvc paysSvc;
    
    @GetMapping(value="/countries")
    public ResponseEntity<List<PaysDto>> getcountries() {
        return new ResponseEntity<>(paysSvc.list(), HttpStatus.OK);
    }

    @GetMapping(value="/country/{id}")
    public ResponseEntity<PaysDto> geCountry(@PathVariable Long id) {
        return new ResponseEntity<>(paysSvc.byId(id), HttpStatus.OK);
    }

    @PostMapping(value="/country")
    public ResponseEntity<PaysDto> newCountry(@RequestBody PaysDto paysDto) {
        return new ResponseEntity<>(paysSvc.save(paysDto), HttpStatus.CREATED);
    }  

    @DeleteMapping(value="/country/{id}")
    public ResponseEntity<PaysDto> delCountry(@PathVariable Long id) {
        try {
            paysSvc.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
        
}
