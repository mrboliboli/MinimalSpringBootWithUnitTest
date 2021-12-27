package fr.fab.test.ut.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
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

import fr.fab.test.dto.PaysDto;
import fr.fab.test.models.Pays;
import fr.fab.test.models.Ville;
import fr.fab.test.repository.PaysRepository;
import fr.fab.test.services.PaysSvcImpl;


/**
 * classe de TU pour les PAYS
 * Voir {@link VilleSvcTest} pour plus d'info sur la configuration, 
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PaysSvcTest {


    private List<Pays> countries;
    /**
     * @InjectMocks indique la classe dans laquelle on va injecter les mocks
     */
    @Autowired
    @InjectMocks
    private PaysSvcImpl paysSvc;

    /**
     * Les mocks que l'on va injecter
     */
    @Mock   
    private PaysRepository paysRepository;   



    @BeforeAll 
	public void init() {
        /**
         * ouverture de Mock a injecter dans le service a tester
         */
		MockitoAnnotations.openMocks(this);

        countries = new ArrayList<>(); 
        Pays pooLand = new Pays(1l, "pooLand", "pl", new HashSet<>());
        pooLand.getVilles().add(new Ville(1l, "poo", pooLand));
        pooLand.getVilles().add(new Ville(2l, "pee", pooLand));
        pooLand.getVilles().add(new Ville(3l, "poopee", pooLand));
        countries.add(pooLand);
        Pays france = new Pays(1l, "pooLand", "fr", new HashSet<>());
        france.getVilles().add(new Ville(4l, "Paris", france));
        france.getVilles().add(new Ville(5l, "Tours", france));
        france.getVilles().add(new Ville(6l, "Marseille", france));
        countries.add(france);
        Pays allemagne = new Pays(1l, "pooLand", "de", new HashSet<>());
        allemagne.getVilles().add(new Ville(7l, "Berlin", allemagne));
        allemagne.getVilles().add(new Ville(8l, "Munich", allemagne));
        allemagne.getVilles().add(new Ville(9l, "Hanbourg", allemagne));
        countries.add(allemagne);
	}


    @Test
    void getAllPays_toDto(){

        /**
         * on mock le service pour par dependre des autres couches
         */
        when(paysRepository.findAll()).thenReturn(countries);
        // recuperation du resultats avec un stream() pour orderBy
        List<PaysDto> paysDtos = paysSvc.list().stream().sorted(Comparator.comparingLong(PaysDto::getId)).collect(Collectors.toList());

        //les Assertions
        assertEquals(3, paysDtos.size());
        /**
         * https://www.baeldung.com/mockito-verify
         */
        verify(paysRepository, times(1)).findAll();
        assertEquals("pooLand", paysDtos.get(0).getNomPays());

    }
    
    @Test
    void getVillesById_toDto(){
        Pays pooLand = countries.get(0);
        when(paysRepository.getById(anyLong())).thenReturn(pooLand);
        // recuperation du resultats
        PaysDto paysDto = paysSvc.byId(1l);
        assertNotNull(paysDto);
        assertEquals(pooLand.getNomPays(), paysDto.getNomPays());
    }

    @Test
    void CreateVilles_fromDto_toDto(){
        Pays pooLand = countries.get(0);
        /**
         * Ici, on indique au mock de repository d'accepter en entrée n'importe quelle instance de Ville
         * https://mkyong.com/spring-boot/spring-mockito-unable-to-mock-save-method/ 
         */
        when(paysRepository.save(any(Pays.class))).thenReturn(pooLand);
        /**
         * comme on test avec les dto, le parametre du mock et celui du service sont different (on passe d'un dto au service testé a un entity pour le repository mocké), 
         * Mokito cree une alerte d'ou @MockitoSettings(strictness = Strictness.LENIENT) pour pas remonter d'exception et lui indiquer que le comportement est normal
         */
        PaysDto paysDto = paysSvc.save(new PaysDto(0l, "pooLand", "pl", new ArrayList<>()));
        assertNotNull(paysDto);
        assertEquals(pooLand.getNomPays(), paysDto.getNomPays());
    }
    
}
