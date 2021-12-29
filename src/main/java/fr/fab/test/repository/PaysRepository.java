package fr.fab.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.fab.test.models.Pays;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {

    /**
     * Creation de la methode de recherche par nom 
     * 
     * https://bushansirgur.in/spring-data-jpa-finder-methods-by-field-name-with-examples/
     * 
     * @param nomPays
     * @return
     */
    Optional<Pays> findByNomPays(String nomPays);
    
}
