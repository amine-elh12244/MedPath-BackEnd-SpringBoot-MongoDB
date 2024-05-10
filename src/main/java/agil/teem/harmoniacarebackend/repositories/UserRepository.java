package agil.teem.harmoniacarebackend.repositories;

import agil.teem.harmoniacarebackend.entities.Role;
import agil.teem.harmoniacarebackend.entities.user;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<user, String> , UserDetailsService {



    @Query("{ 'role.id' : ?0 }")
    List<user> findByRoleId(String roleId);

    Optional<user> findByEmail(String email);


    @Override
    default UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }




}