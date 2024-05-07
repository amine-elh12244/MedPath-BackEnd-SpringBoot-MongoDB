package agil.teem.harmoniacarebackend.Controllers;


import agil.teem.harmoniacarebackend.entities.Role;
import agil.teem.harmoniacarebackend.services.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService RoleService;


    public RoleController(RoleService RoleService) {
        this.RoleService = RoleService;
    }

    // Retrieve all results
    @GetMapping
    public List<Role> getAllResults() {
        return RoleService.getAllRoles();
    }
}
