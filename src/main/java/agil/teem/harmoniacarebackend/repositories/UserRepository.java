package agil.teem.harmoniacarebackend.repositories;

import agil.teem.harmoniacarebackend.entities.Role;
import agil.teem.harmoniacarebackend.entities.user;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends MongoRepository<user, String> {



    @Query("{ 'role.id' : ?0 }")
    List<user> findByRoleId(String roleId);

    user findByEmail(String email);



}