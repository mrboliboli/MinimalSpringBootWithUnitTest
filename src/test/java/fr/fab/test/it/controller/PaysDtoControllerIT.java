package fr.fab.test.it.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import fr.fab.test.dto.PaysDto;
import fr.fab.test.dto.VilleDto;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
@TestPropertySource("classpath:application-test.properties")
@DirtiesContext
public class PaysDtoControllerIT {

    /**
     * Attention verifier la necessité d'ajouter le @DirtiesContext
     * car on a modifier la source de properties
     */

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;


    /**
     * creation d'un dto, envoi du dto via la requette http
     * test des methode de conversion de DTO avec le Model Mapper
     */
    @Test 
    void should_create_persistant_entity_from_controller() throws Exception {
        PaysDto paysDto = new PaysDto("Pooland", "pl", new ArrayList<>());
       /*  */ VilleDto villeDto = new VilleDto("pee", 0l);
        paysDto.getVilles().add(villeDto);
        log.debug(objectMapper.writeValueAsString(paysDto));
        mockMvc.perform(post("/pays-dto/country")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(paysDto)))
            .andExpect(status().isCreated());
    }


    @Test
    public void getAllEmployeesAPI() throws Exception {
        mockMvc.perform(get("/pays-dto/countries")
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[*].id").isNotEmpty());
    }
    



    @Test
    public void getUsersByIdAPI() throws Exception {
        mockMvc.perform(get("/pays-dto/country/{id}", 1)
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.nomPays", is("Pooland")));
    }
    
}
