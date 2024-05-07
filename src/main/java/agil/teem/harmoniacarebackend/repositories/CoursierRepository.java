package agil.teem.harmoniacarebackend.repositories;

import agil.teem.harmoniacarebackend.entities.Coursier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CoursierRepository extends MongoRepository<Coursier, String> {

    Optional<Coursier> findById(String id);

}

