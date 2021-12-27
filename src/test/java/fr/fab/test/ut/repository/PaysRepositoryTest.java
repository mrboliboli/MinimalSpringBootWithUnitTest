package fr.fab.test.ut.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * test du repository des pays
 * ideal pour checker la persistance en cascade avec les villes
 */
@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class PaysRepositoryTest {
    
}
