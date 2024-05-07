package agil.teem.harmoniacarebackend.services;

import agil.teem.harmoniacarebackend.entities.Result;
import agil.teem.harmoniacarebackend.entities.Role;
import agil.teem.harmoniacarebackend.repositories.ResultRepository;
import agil.teem.harmoniacarebackend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {


    private final RoleRepository roleRepository;


    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    // Retrieve all results
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

}
