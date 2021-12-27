package fr.fab.test.ut.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.fab.test.models.User;
import fr.fab.test.repository.UserRepository;

/**
 * Exemple de tests des classes repository
 * sources : 
 * https://blog.devgenius.io/spring-boot-deep-dive-on-unit-testing-92bbdf549594#acc0
 */
@ExtendWith(MockitoExtension.class)
@DataJpaTest // https://www.baeldung.com/spring-boot-testing#integration-testing-with-datajpatest 
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    /**
     * a l'initialisation on ajoute des User dans la Base H2 in memory crée par @DataJpaTest
     */
    @BeforeEach
    void initUseCase() {
        List<User> customers = Arrays.asList(
                new User("DUS", "Jean-Claude", 40, "PARIS", 'M'),
                new User("CONVENANT", "Jean-Claude", 45, "PARIS", 'M')
        );
        userRepository.saveAll(customers);
    }

    /**
     * a la fin on detruit le occurences crées
     */
    @AfterEach
    public void destroyAll(){
        userRepository.deleteAll();
    }

    /**
     * Test si on arrive a persister quelques Users
     */
    @Test
    void saveAll_success() {
        List<User> users = Arrays.asList(
            new User("SKYWALKER", "Luke", 19, "Mos Esley", 'M'),
            new User("CHAT", "Papyrus", 2, "Manthelan", 'M'),
            new User("CHAT", "Ohana", 3, "Manthelan", 'F')
        );
        Iterable<User> allUsers = userRepository.saveAll(users);

        AtomicInteger validIdFound = new AtomicInteger();
        allUsers.forEach(customer -> {
            if(customer.getId()>0){
                validIdFound.getAndIncrement();
            }
        });
        assertEquals(3, validIdFound.intValue());
    }

    /**
     * maintenant si on en recupere
     */
    @Test
    void findAll_success() {
        Iterable<User> allUsers = userRepository.findAll();
        assertTrue(StreamSupport.stream(allUsers.spliterator(), false).count()>=1l);
    }
    
}
