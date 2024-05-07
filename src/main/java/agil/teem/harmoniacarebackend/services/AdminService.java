package agil.teem.harmoniacarebackend.services;

import agil.teem.harmoniacarebackend.entities.Admin;
import agil.teem.harmoniacarebackend.entities.Coursier;
import agil.teem.harmoniacarebackend.entities.Role;
import agil.teem.harmoniacarebackend.entities.user;
import agil.teem.harmoniacarebackend.repositories.AdminRepository;
import agil.teem.harmoniacarebackend.repositories.CoursierRepository;
import agil.teem.harmoniacarebackend.repositories.RoleRepository;
import agil.teem.harmoniacarebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CoursierRepository coursierRepository;



    public AdminService(AdminRepository adminRepository , UserRepository userRepository, RoleRepository roleRepository , CoursierRepository coursierRepository) {
        this.adminRepository = adminRepository;
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.coursierRepository = coursierRepository;


    }

    // Create a new admin
    public Admin createAdmin(Admin admin) {
//          String password = admin.getPassword();
//         admin.setPassword(passwordEncoder.encode(password));
        return adminRepository.save(admin);
    }

    // Retrieve all admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public List<user> getUsersByRole(String roleName) {
        Role role = roleRepository.findByRoleName(roleName);
        if (role == null) {
            throw new RuntimeException("Role not found: " + roleName);
        }

        String roleId = role.getId();
        System.out.println("Role ID: " + roleId); // Log the role ID

        List<user> users = userRepository.findByRoleId(roleId);
        System.out.println("Found " + users.size() + " users.");

        return users;
    }


    // Retrieve an admin by its ID
    public Optional<Admin> getAdminById(String id) {
        return adminRepository.findById(id);
    }

    // Update an admin
    public Admin updateAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Delete an admin
    public void deleteAdmin(String id) {
        adminRepository.deleteById(id);
    }

    public user createUser(user User) {
        User.setRole(new HashSet<>()); // Initialize the Set
        return userRepository.save(User);

    }

    // Retrieve all users
    public List<user> getAllUsers() {
        List<user> users = userRepository.findAll();
        return users;
    }

    // Retrieve a user by its ID
    public Optional<user> getUserById(String id) {
        return userRepository.findById(id);
    }

    // Update a user
    public user updateUser(user User) {
        return userRepository.save(User);
    }

    // Delete a user
    public void deleteUser(String id) {
        userRepository.deleteById(id);
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