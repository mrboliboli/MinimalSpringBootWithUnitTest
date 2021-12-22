package fr.fab.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.fab.test.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    
}
