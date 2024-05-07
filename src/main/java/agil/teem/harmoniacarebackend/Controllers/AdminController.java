package agil.teem.harmoniacarebackend.Controllers;

import agil.teem.harmoniacarebackend.entities.Admin;
import agil.teem.harmoniacarebackend.entities.Coursier;
import agil.teem.harmoniacarebackend.entities.user;
import agil.teem.harmoniacarebackend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Create a new admin
    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    // Retrieve all admins
    @GetMapping("/users={role}")
    public List<user> getAllAdmins(@PathVariable String role) {
        return adminService.getUsersByRole(role);
    }

    // Retrieve an admin by its ID
    @GetMapping("/{id}")
    public Optional<Admin> getAdminById(@PathVariable String id) {
        return adminService.getAdminById(id);
    }

    // Update an admin
    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable String id, @RequestBody Admin admin) {
        // You might want to set the ID of the Admin object to the ID in the path
        // to ensure they are the same
        admin.setId(id);
        return adminService.updateAdmin(admin);
    }

    // Delete an admin
    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable String id) {
        adminService.deleteAdmin(id);
    }


    @PostMapping("/users")
    public user createUser(@RequestBody user User) {
        return adminService.createUser(User);
    }

    // Retrieve all users
    @GetMapping("/users")
    public List<user> getAllUsers() {
        return adminService.getAllUsers();
    }




    // Retrieve a user by its ID
    @GetMapping("users/{id}")
    public Optional<user> getUserById(@PathVariable String id) {
        return adminService.getUserById(id);
    }

    // Update a user
    @PutMapping("users/{id}")
    public user updateUser(@PathVariable String id, @RequestBody user User) {
        // You might want to set the ID of the User object to the ID in the path
        // to ensure they are the same
        User.setId(id);
        return adminService.updateUser(User);
    }

    // Delete a user
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
        adminService.deleteUser(id);
    }

    @PostMapping("/coursiers")
    public Coursier createCoursier(@RequestBody Coursier coursier) {
        return adminService.createCoursier(coursier);
    }

    // Retrieve all coursiers
    @GetMapping("/coursiers")
    public List<Coursier> getAllCoursiers() {
        return adminService.getAllCoursiers();
    }

    // Retrieve a coursier by its ID
    @GetMapping("/coursiers/{id}")
    public Optional<Coursier> getCoursierById(@PathVariable String id) {
        return adminService.getCoursierById(id);
    }

    // Update a coursier
    @PutMapping("/coursiers/{id}")
    public Coursier updateCoursier(@PathVariable String id, @RequestBody Coursier coursier) {
        coursier.setId(id);
        return adminService.updateCoursier(coursier);
    }

    // Delete a coursier
    @DeleteMapping("/coursiers/{id}")
    public void deleteCoursier(@PathVariable String id) {
            adminService.deleteCoursier(id);
    }
}