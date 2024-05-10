package agil.teem.harmoniacarebackend.repositories;

import agil.teem.harmoniacarebackend.entities.Session;
import agil.teem.harmoniacarebackend.entities.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SessionRepository extends MongoRepository<Session, String> {

    Optional<Session> findByToken(String token);

}
