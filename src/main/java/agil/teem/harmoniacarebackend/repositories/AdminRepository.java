package agil.teem.harmoniacarebackend.repositories;

import agil.teem.harmoniacarebackend.entities.Admin;
import agil.teem.harmoniacarebackend.entities.user;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AdminRepository extends MongoRepository<Admin, String> {
    // Add any additional query methods herev




}