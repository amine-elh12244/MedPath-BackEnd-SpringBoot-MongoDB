package agil.teem.harmoniacarebackend.repositories;

import agil.teem.harmoniacarebackend.entities.Admin;
import agil.teem.harmoniacarebackend.entities.Medecin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MedecinRepository extends MongoRepository<Medecin, String> {
    // Add any additional query methods here



}
