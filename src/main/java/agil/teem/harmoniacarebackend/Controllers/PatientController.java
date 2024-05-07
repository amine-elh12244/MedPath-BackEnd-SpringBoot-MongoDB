package agil.teem.harmoniacarebackend.Controllers;

import agil.teem.harmoniacarebackend.entities.Patient;
import agil.teem.harmoniacarebackend.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Create a new patient
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    // Retrieve all patients
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    // Retrieve a patient by its ID
    @GetMapping("/{id}")
    public Optional<Patient> getPatientById(@PathVariable String id) {
        return patientService.getPatientById(id);
    }

    // Update a patient
    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable String id, @RequestBody Patient patient) {
        // You might want to set the ID of the Patient object to the ID in the path
        // to ensure they are the same
        patient.setId(id);
        return patientService.updatePatient(patient);
    }

    // Delete a patient
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
    }
}