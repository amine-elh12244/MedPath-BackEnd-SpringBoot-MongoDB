package agil.teem.harmoniacarebackend.repositories;

import agil.teem.harmoniacarebackend.entities.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<Patient, String> {
}