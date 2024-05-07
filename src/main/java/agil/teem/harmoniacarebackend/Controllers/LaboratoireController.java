package agil.teem.harmoniacarebackend.Controllers;

import agil.teem.harmoniacarebackend.entities.Laboratoire;
import agil.teem.harmoniacarebackend.services.LaboratoireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/laboratoires")
public class LaboratoireController {

    private final LaboratoireService laboratoireService;

    @Autowired
    public LaboratoireController(LaboratoireService laboratoireService) {
        this.laboratoireService = laboratoireService;
    }

    // Create a new laboratoire
    @PostMapping
    public Laboratoire createLaboratoire(@RequestBody Laboratoire laboratoire) {
        return laboratoireService.createLaboratoire(laboratoire);
    }

    // Retrieve all laboratoires
    @GetMapping
    public List<Laboratoire> getAllLaboratoires() {
        return laboratoireService.getAllLaboratoires();
    }

    // Retrieve a laboratoire by its ID
    @GetMapping("/{id}")
    public Optional<Laboratoire> getLaboratoireById(@PathVariable String id) {
        return laboratoireService.getLaboratoireById(id);
    }

    // Update a laboratoire
    @PutMapping("/{id}")
    public Laboratoire updateLaboratoire(@PathVariable String id, @RequestBody Laboratoire laboratoire) {
        // You might want to set the ID of the Laboratoire object to the ID in the path
        // to ensure they are the same
        laboratoire.setId(id);
        return laboratoireService.updateLaboratoire(laboratoire);
    }

    // Delete a laboratoire
    @DeleteMapping("/{id}")
    public void deleteLaboratoire(@PathVariable String id) {
        laboratoireService.deleteLaboratoire(id);
    }
}