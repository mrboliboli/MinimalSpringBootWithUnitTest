package fr.fab.test.ut.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import fr.fab.test.dto.UserDto;
import fr.fab.test.services.UserSvc;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class UserSvcTest{
    
    @Autowired
    private UserSvc userSvc;



    /**
     * creation d'un dto, envoi du dto via la requette http
     * test des methode de conversion de DTO avec le Model Mapper
     */
    @Test 
    void should_get_an_entity_whith_id_from_dto() throws Exception{
        // MockMvcResultMatchers.status().isOk();
        UserDto userDto = new UserDto("Stark", "Tony", 50, "Malibu", 'M');
        UserDto res = userSvc.save(userDto);
        assertTrue(res.getId()>=1);
        assertEquals(userDto.getNom(), res.getNom());
        assertEquals(userDto.getPrenom(), res.getPrenom());
        assertEquals(userDto.getAge(), res.getAge());
        assertEquals(userDto.getVille(), res.getVille());
        assertEquals(userDto.getGenre(), res.getGenre());

    }
    

}
