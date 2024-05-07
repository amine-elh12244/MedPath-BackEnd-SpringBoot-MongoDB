package agil.teem.harmoniacarebackend.services;

import agil.teem.harmoniacarebackend.entities.Laboratoire;
import agil.teem.harmoniacarebackend.repositories.LaboratoireRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratoireService {

    private final LaboratoireRepository laboratoireRepository;
    //private PasswordEncoder passwordEncoder;


    @Autowired
    public LaboratoireService(LaboratoireRepository laboratoireRepository) {
        this.laboratoireRepository = laboratoireRepository;
    }

    // Create a new laboratoire
    public Laboratoire createLaboratoire(Laboratoire laboratoire) {
//
//        String password = laboratoire.getPassword();
//        laboratoire.setPassword(passwordEncoder.encode(password));
        return laboratoireRepository.save(laboratoire);
    }

    // Retrieve all laboratoires
    public List<Laboratoire> getAllLaboratoires() {
        return laboratoireRepository.findAll();
    }

    // Retrieve a laboratoire by its ID
    public Optional<Laboratoire> getLaboratoireById(String id) {
        return laboratoireRepository.findById(id);
    }

    // Update a laboratoire
    public Laboratoire updateLaboratoire(Laboratoire laboratoire) {
        return laboratoireRepository.save(laboratoire);
    }

    // Delete a laboratoire
    public void deleteLaboratoire(String id) {
        laboratoireRepository.deleteById(id);
    }
}