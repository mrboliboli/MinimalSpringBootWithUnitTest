package fr.fab.test.it.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import fr.fab.test.dto.UserDto;
import lombok.extern.log4j.Log4j2;

/**
 * configuration de mockMvc simplifié voir documentation ici
 * https://spring.io/guides/gs/testing-web/
 * 
 * voir la pour l'utilisation de jsonPath
 * https://www.petrikainulainen.net/programming/spring-framework/integration-testing-of-spring-mvc-applications-write-clean-assertions-with-jsonpath/
 */
@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
@DirtiesContext
public class UserControllerIT {
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
        UserDto userDto = new UserDto("Stark", "Tony", 50, "Malibu", 'M');
        log.debug(objectMapper.writeValueAsString(userDto));
        mockMvc.perform(post("/user-management/user")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(userDto)))
            .andExpect(status().isCreated());
    }


    @Test
    public void getAllEmployeesAPI() throws Exception {
        mockMvc.perform(get("/user-management/users")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[*].id").isNotEmpty());
    }
    
    @Test
    public void getUsersByIdAPI() throws Exception {
        mockMvc.perform(get("/user-management/user/{id}", 1)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.nom", is("Stark")));
    }

}
