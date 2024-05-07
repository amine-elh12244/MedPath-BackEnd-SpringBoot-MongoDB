package agil.teem.harmoniacarebackend.services;

import agil.teem.harmoniacarebackend.entities.Medecin;
import agil.teem.harmoniacarebackend.repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedecinService {

    private final MedecinRepository medecinRepository;
    // private PasswordEncoder passwordEncoder;


    @Autowired
    public MedecinService(MedecinRepository medecinRepository) {
        this.medecinRepository = medecinRepository;
    }

    // Create a new medecin
    public Medecin createMedecin(Medecin medecin) {
//        String password = medecin.getPassword();
//        medecin.setPassword(passwordEncoder.encode(password));
        return medecinRepository.save(medecin);
    }

    // Retrieve all medecins
    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }

    // Retrieve a medecin by its ID
    public Optional<Medecin> getMedecinById(String id) {
        return medecinRepository.findById(id);
    }

    // Update a medecin
    public Medecin updateMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    // Delete a medecin
    public void deleteMedecin(String id) {
        medecinRepository.deleteById(id);
    }
}