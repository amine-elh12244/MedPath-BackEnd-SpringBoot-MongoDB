package agil.teem.harmoniacarebackend.services;

import agil.teem.harmoniacarebackend.entities.Laboratoire;
import agil.teem.harmoniacarebackend.entities.Role;
import agil.teem.harmoniacarebackend.repositories.LaboratoireRepository;
import agil.teem.harmoniacarebackend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LaboratoireService {

    private final LaboratoireRepository laboratoireRepository;
    private final RoleRepository roleRepository;
    //private PasswordEncoder passwordEncoder;


    @Autowired
    public LaboratoireService(LaboratoireRepository laboratoireRepository , RoleRepository roleRepository) {
        this.laboratoireRepository = laboratoireRepository;
        this.roleRepository = roleRepository;
    }

    // Create a new laboratoire
    public Laboratoire createLaboratoire(Laboratoire laboratoire) {
        // Récupérer les rôles de l'utilisateur
        Set<Role> userRoles = laboratoire.getRole();

        // Vérifier chaque rôle pour existence
        for (Role userRole : userRoles) {
            String roleName = userRole.getRoleName();
            Role existingRole = roleRepository.findByRoleName(roleName);

            if (existingRole != null) {
                // Si le rôle existe, l'assigner à l'utilisateur
                laboratoire.getRole().remove(userRole); // Retirer le rôle existant de la collection
                laboratoire.getRole().add(existingRole); // Ajouter le rôle existant
            } else {
                // Si le rôle n'existe pas, créer un nouveau rôle et l'assigner à l'utilisateur
                roleRepository.save(userRole);
            }
        }

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