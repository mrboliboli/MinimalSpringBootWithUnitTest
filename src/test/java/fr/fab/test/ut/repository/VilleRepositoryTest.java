package fr.fab.test.ut.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.fab.test.models.Pays;
import fr.fab.test.models.Ville;
import fr.fab.test.repository.PaysRepository;
import fr.fab.test.repository.VilleRepository;

/**
 * Tests pour le repository VilleRepository
 * voir {@link UserRepositoryTest} pour plus d'infos 
 * Ici , on va se concentrer sur @BeforeAll a la place @BeforeEach
 */

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // https://www.baeldung.com/java-beforeall-afterall-non-static
public class VilleRepositoryTest {

    @Autowired 
    private VilleRepository villeRepository;
   
    /**
     * obligé d'importer le paysRepository sinon espagne est Transcient
     * Existe t'il un moyen de faire plus propre?
     */
    @Autowired
    private PaysRepository paysRepository;

    private Pays espagne;

    @BeforeAll
    void init(){
        espagne = new Pays("Espagne", "es", new HashSet<>());
        paysRepository.save(espagne);
        villeRepository.saveAll(Arrays.asList(
            new Ville("Valence", espagne),
            new Ville("Seville", espagne)
        ));



    }

    @AfterAll
    void destroy(){
        villeRepository.deleteAll();
    }

    /**
     * on check si le @BeforeAll fonctionne
     * on doit trouver 4 villes pour l'espagne
     */
    @Test
    public void testLoadDataForTestClass() {
        assertEquals(2, villeRepository.findAll().size());
    }

    @Test
    void insertOneVilleToEspage_shouldReturnTrue(){
        // creation d'une nouvelle ville
        Ville v = new Ville("Barcelonne", espagne);
        v = villeRepository.save(v);
        assertTrue(v.getId()>0);
    }

    
}
