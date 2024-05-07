package agil.teem.harmoniacarebackend.repositories;

import agil.teem.harmoniacarebackend.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRoleName(String roleName);

}
