package agil.teem.harmoniacarebackend.services;

import agil.teem.harmoniacarebackend.entities.Coursier;
import agil.teem.harmoniacarebackend.repositories.AdminRepository;
import agil.teem.harmoniacarebackend.repositories.CoursierRepository;
import agil.teem.harmoniacarebackend.repositories.RoleRepository;
import agil.teem.harmoniacarebackend.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CoursierService {

    private final CoursierRepository coursierRepository;



    public CoursierService(CoursierRepository coursierRepository) {

        this.coursierRepository = coursierRepository;


    }

    // Create a new coursier
    public Coursier createCoursier(Coursier coursier) {
        return coursierRepository.save(coursier);
    }

    // Retrieve all coursiers
    public List<Coursier> getAllCoursiers() {
        return coursierRepository.findAll();
    }

    // Retrieve a coursier by its ID
    public Optional<Coursier> getCoursierById(String id) {
        return coursierRepository.findById(id);
    }

    // Update a coursier
    public Coursier updateCoursier(Coursier coursier) {
        return coursierRepository.save(coursier);
    }

    // Delete a coursier
    public void deleteCoursier(String id) {
        coursierRepository.deleteById(id);
    }
}
