package fr.fab.test.ut.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.fab.test.models.Pays;
import fr.fab.test.models.Ville;
import fr.fab.test.repository.PaysRepository;
import lombok.extern.log4j.Log4j2;

/**
 * test du repository des pays
 * ideal pour checker la persistance en cascade avec les villes
 * voir {@link UserRepositoryTest} pour plus d'info sur la configuration
 */
@ExtendWith(MockitoExtension.class)
@DataJpaTest
@Log4j2
public class PaysRepositoryTest {

    @Autowired
    private PaysRepository paysRepository;
    
    @BeforeEach
    void initUseCase(){
        Pays p = new Pays("France", "fr", new HashSet<>());
        Ville v  = new Ville("Tours", p);
        p.getVilles().add(v);
        Pays p1 = new Pays("Allemagne", "de", new HashSet<>());
        Ville v1  = new Ville("Berlin", p1);
        p1.getVilles().add(v1);
        Pays p2 = new Pays("Espagne", "es", new HashSet<>());
        Ville v2  = new Ville("Barcelone", p2);
        p2.getVilles().add(v2);
        List<Pays> countries = Arrays.asList(p,p1,p2);
        paysRepository.saveAll(countries);

    }

    @AfterEach
    void destroyAll(){
        paysRepository.deleteAll();
    }


    @Test
    void saveAll_success(){
        Pays p = new Pays("Italie", "it", new HashSet<>());
        Ville v  = new Ville("Rome", p);
        p.getVilles().add(v);
        Pays p1 = new Pays("Suisse", "sw", new HashSet<>());
        Ville v1  = new Ville("Geneve", p1);
        p1.getVilles().add(v1);
        List<Pays> countries = Arrays.asList(p,p1);
        Iterable<Pays> allPays=  paysRepository.saveAll(countries);

        AtomicInteger countId = new AtomicInteger(0);
        allPays.forEach( pays -> {
            if (pays.getId()>0) countId.getAndIncrement();
        });
        assertEquals(2, countId.get());
    }

    @Test
    void findAll_success(){
        List<Pays> allPays=  paysRepository.findAll();
        assertTrue(allPays.size()>0);
    }
    @Test
    void findbyID_success(){
        /**
         * ici, on voit bien que la persistance en cascade fonctionne
         */
        Optional<Pays> res = paysRepository.findById(1l);
        if (log.isDebugEnabled() && res.isPresent()) log.debug(String.format("valeur de la premiere ville du pays %s : %s", res.get().getNomPays(), res.get().getVilles().iterator().next().getNom()));
        assertTrue(res.isPresent());
        assertTrue(res.get().getVilles().size()>0);
        
    }



}
