package agil.teem.harmoniacarebackend.repositories;

import agil.teem.harmoniacarebackend.entities.Result;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResultRepository extends MongoRepository<Result, String> {

}