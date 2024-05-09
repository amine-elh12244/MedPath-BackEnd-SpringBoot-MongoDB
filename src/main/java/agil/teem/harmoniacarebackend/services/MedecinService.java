package agil.teem.harmoniacarebackend.services;

import agil.teem.harmoniacarebackend.entities.Medecin;
import agil.teem.harmoniacarebackend.entities.Role;
import agil.teem.harmoniacarebackend.repositories.MedecinRepository;
import agil.teem.harmoniacarebackend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MedecinService {

    private final MedecinRepository medecinRepository;
    private final RoleRepository roleRepository;
    // private PasswordEncoder passwordEncoder;


    @Autowired
    public MedecinService(MedecinRepository medecinRepository , RoleRepository roleRepository) {
        this.medecinRepository = medecinRepository;
        this.roleRepository = roleRepository;
    }

    // Create a new medecin
    public Medecin createMedecin(Medecin medecin) {
        // Récupérer les rôles de l'utilisateur
        Set<Role> userRoles = medecin.getRole();

        // Vérifier chaque rôle pour existence
        for (Role userRole : userRoles) {
            String roleName = userRole.getRoleName();
            Role existingRole = roleRepository.findByRoleName(roleName);

            if (existingRole != null) {
                // Si le rôle existe, l'assigner à l'utilisateur
                medecin.getRole().remove(userRole); // Retirer le rôle existant de la collection
                medecin.getRole().add(existingRole); // Ajouter le rôle existant
            } else {
                // Si le rôle n'existe pas, créer un nouveau rôle et l'assigner à l'utilisateur
                roleRepository.save(userRole);
            }
        }

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