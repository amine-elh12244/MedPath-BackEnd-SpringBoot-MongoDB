package agil.teem.harmoniacarebackend.repositories;

import agil.teem.harmoniacarebackend.entities.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepository extends MongoRepository<Test, String> {
}