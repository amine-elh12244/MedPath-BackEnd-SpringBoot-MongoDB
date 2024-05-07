package agil.teem.harmoniacarebackend.services;

import agil.teem.harmoniacarebackend.entities.Patient;
import agil.teem.harmoniacarebackend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Create a new patient
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Retrieve all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Retrieve a patient by its ID
    public Optional<Patient> getPatientById(String id) {
        return patientRepository.findById(id);
    }

    // Update a patient
    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Delete a patient
    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }
}