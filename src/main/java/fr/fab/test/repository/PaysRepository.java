package fr.fab.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.fab.test.models.Pays;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {
    
}
