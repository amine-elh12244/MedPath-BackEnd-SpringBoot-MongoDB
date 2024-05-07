package agil.teem.harmoniacarebackend.Controllers;

import agil.teem.harmoniacarebackend.entities.Medecin;
import agil.teem.harmoniacarebackend.services.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medecins")
public class MedecinController {

    private final MedecinService medecinService;

    @Autowired
    public MedecinController(MedecinService medecinService) {
        this.medecinService = medecinService;
    }

    // Create a new medecin
    @PostMapping
    public Medecin createMedecin(@RequestBody Medecin medecin) {
        return medecinService.createMedecin(medecin);
    }

    // Retrieve all medecins
    @GetMapping
    public List<Medecin> getAllMedecins() {
        return medecinService.getAllMedecins();
    }

    // Retrieve a medecin by its ID
    @GetMapping("/{id}")
    public Optional<Medecin> getMedecinById(@PathVariable String id) {
        return medecinService.getMedecinById(id);
    }

    // Update a medecin
    @PutMapping("/{id}")
    public Medecin updateMedecin(@PathVariable String id, @RequestBody Medecin medecin) {
        // You might want to set the ID of the Medecin object to the ID in the path
        // to ensure they are the same
        medecin.setId(id);
        return medecinService.updateMedecin(medecin);
    }

    // Delete a medecin
    @DeleteMapping("/{id}")
    public void deleteMedecin(@PathVariable String id) {
        medecinService.deleteMedecin(id);
    }
}