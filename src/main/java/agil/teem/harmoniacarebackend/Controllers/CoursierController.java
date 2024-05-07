package agil.teem.harmoniacarebackend.Controllers;


import agil.teem.harmoniacarebackend.entities.Coursier;
import agil.teem.harmoniacarebackend.services.CoursierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coursiers")
public class CoursierController {

    private final CoursierService coursierService;

    @Autowired
    public CoursierController(CoursierService coursierService) {
        this.coursierService = coursierService;
    }

    // Create a new coursier
    @PostMapping
    public ResponseEntity<Coursier> createCoursier(@RequestBody Coursier coursier) {
        Coursier createdCoursier = coursierService.createCoursier(coursier);
        return new ResponseEntity<>(createdCoursier, HttpStatus.CREATED);
    }

    // Retrieve all coursiers
    @GetMapping
    public ResponseEntity<List<Coursier>> getAllCoursiers() {
        List<Coursier> allCoursiers = coursierService.getAllCoursiers();
        return new ResponseEntity<>(allCoursiers, HttpStatus.OK);
    }

    // Retrieve a coursier by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Coursier> getCoursierById(@PathVariable String id) {
        Optional<Coursier> coursier = coursierService.getCoursierById(id);
        return coursier.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a coursier
    @PutMapping("/{id}")
    public ResponseEntity<Coursier> updateCoursier(@PathVariable String id, @RequestBody Coursier coursier) {
        coursier.setId(id);
        Coursier updatedCoursier = coursierService.updateCoursier(coursier);
        return new ResponseEntity<>(updatedCoursier, HttpStatus.OK);
    }

    // Delete a coursier
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoursier(@PathVariable String id) {
        coursierService.deleteCoursier(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

