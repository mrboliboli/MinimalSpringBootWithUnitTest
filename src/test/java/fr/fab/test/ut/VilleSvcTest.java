package fr.fab.test.ut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.fab.test.dto.VilleDto;
import fr.fab.test.models.Pays;
import fr.fab.test.models.Ville;
import fr.fab.test.repository.PaysRepository;
import fr.fab.test.repository.VilleRepository;
import fr.fab.test.services.VilleSvcImpl;

/**
 * test de la couche de service
 * https://blog.devgenius.io/spring-boot-deep-dive-on-unit-testing-92bbdf549594#1c9b
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT) // on demande a mokito le silence pour le dernier test
public class VilleSvcTest {

    /**
     * @InjectMocks indique la classe dans laquelle on va injecter les mocks
     */
    @Autowired
    @InjectMocks
    private VilleSvcImpl villeSvc;

    /**
     * Les mocks que l'on va injecter
     */
    @Mock
    private VilleRepository villeRepository;
    @Mock   
    private PaysRepository paysRepository;   



    @BeforeAll 
	public void init() {
		MockitoAnnotations.openMocks(this);

	}


    @Test
    void getAllVilles_toDto(){
        Pays pooLand = new Pays(1l, "pooLand", "pl", new HashSet<>());
        pooLand.getVilles().add(new Ville(1l, "poo", pooLand));
        pooLand.getVilles().add(new Ville(2l, "pee", pooLand));
        pooLand.getVilles().add(new Ville(3l, "poopee", pooLand));
        List<Ville> villesTmp = pooLand.getVilles().stream().toList();
        /**
         * on mock le service pour par dependre des autres couches
         */
        when(villeRepository.findAll()).thenReturn(villesTmp);
        // recuperation du resultats avec un stream() pour orderBy
        List<VilleDto> villes = villeSvc.list().stream().sorted(Comparator.comparingLong(VilleDto::getId)).collect(Collectors.toList());

        //les Assertions
        assertEquals(villes.size(), 3);
        /**
         * https://www.baeldung.com/mockito-verify
         */
        verify(villeRepository, times(1)).findAll();
        assertEquals("poo", villes.get(0).getNom());

    }
    @Test
    void getVillesById_toDto(){
        Pays pooLand = new Pays(1l, "pooLand", "pl", new HashSet<>());
        when(villeRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(new Ville(1l, "poo", pooLand)));
        // recuperation du resultats
        VilleDto ville = villeSvc.byId(1l);
        assertNotNull(ville);
        assertEquals("poo", ville.getNom());
    }
    @Test
    void CreateVilles_fromDto_toDto(){
        
        Pays pooLand = new Pays(1l, "pooLand", "pl", new HashSet<>());
        Ville villeOut = new Ville(1l,"poo", pooLand);
        Ville villeIn = new Ville(0l,"poo", pooLand);
        pooLand.getVilles().add(villeOut);
        when(paysRepository.getById(1l)).thenReturn(pooLand);
        when(villeRepository.save(villeIn)).thenReturn(villeOut);
        // comme on test avec les dto, le parametre du mock et celui du service sont different, 
        // d'ou @MockitoSettings(strictness = Strictness.LENIENT) pour pas remonter d'exception
        VilleDto villeDto = villeSvc.save(new VilleDto(0l, "poo", 1l));
        assertNotNull(villeDto);
        assertEquals("poo", villeDto.getNom());
    }
    
}
