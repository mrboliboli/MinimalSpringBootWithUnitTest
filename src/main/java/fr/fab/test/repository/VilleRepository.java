package fr.fab.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.fab.test.models.Ville;

@Repository
public interface VilleRepository extends JpaRepository<Ville,Long>{
    
}
