package agil.teem.harmoniacarebackend.repositories;

import agil.teem.harmoniacarebackend.entities.Admin;
import agil.teem.harmoniacarebackend.entities.Laboratoire;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LaboratoireRepository extends MongoRepository<Laboratoire, String> {
    // Add any additional query methods here



}