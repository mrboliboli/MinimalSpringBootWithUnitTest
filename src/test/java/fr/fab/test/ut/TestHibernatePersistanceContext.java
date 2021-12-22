package fr.fab.test.ut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;

import fr.fab.test.models.User;
import fr.fab.test.repository.UserRepository;
import lombok.extern.log4j.Log4j2;


/**
 * le DirtiesContext permet d'indiquer que le context spring est bricolé le ClassMode.BEFORE_CLASS permet de definr ce contexte avant le chargement
 * de la classe ici pour recharger un nouvelle instance de bdd
 * 
 * https://docs.spring.io/spring-framework/docs/4.2.6.RELEASE/javadoc-api/org/springframework/test/annotation/DirtiesContext.html
 */
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@Log4j2
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class TestHibernatePersistanceContext {

    @Autowired
    private UserRepository userRepository;

    @Test
    void should_count_entity_is_greater_than_zero(){
        List<User> users = new ArrayList<>();
 
        users.add(new User("DUS", "Jean-Claude", 42, "Paris", 'M'));
        users.add(new User("PROVOST", "Fabien", 39, "Parçay Meslay", 'M'));
        users.add(new User("PROVOST", "Jules", 10, "Parçay Meslay", 'M'));
        users.add(new User("PROVOST", "Naomi", 13, "Parçay Meslay", 'F'));
        users.add(new User("ANDRY", "Annabelle", 37, "Parçay Meslay", 'F'));
        var res = userRepository.saveAll(users);

        assertTrue(StreamSupport.stream(res.spliterator(), false).count()>0);
    }

    @Test
    void should_have_all_users(){
        var users = userRepository.findAll();
        if (log.isDebugEnabled()){
            log.debug(String.format("nombre d'utilisateurs", StreamSupport.stream(users.spliterator(),false).count()));
            users.forEach(u -> log.debug(u.toString()));
        }
        assertEquals(5, StreamSupport.stream(users.spliterator(),false).count());
    }

    @Test
    void should_have_jules_with_three_id(){
        Optional<User> poo =  userRepository.findById(1L);
        if (log.isDebugEnabled()){
            log.debug(String.format("L'optional possede une valeur : %b", poo.isPresent()));
            if (poo.isPresent()) log.debug(poo.get().toString());;
        }
        assertTrue(poo.isPresent());
        assertEquals("DUS", poo.get().getNom());
    }

    
}
