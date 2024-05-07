package agil.teem.harmoniacarebackend.services;

import agil.teem.harmoniacarebackend.entities.Test;
import agil.teem.harmoniacarebackend.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    // Create a new test
    public Test createTest(Test test) {
        return testRepository.save(test);
    }

    // Retrieve all tests
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    // Retrieve a test by its ID
    public Optional<Test> getTestById(String id) {
        return testRepository.findById(id);
    }

    // Update a test
    public Test updateTest(Test test) {
        return testRepository.save(test);
    }

    // Delete a test
    public void deleteTest(String id) {
        testRepository.deleteById(id);
    }
}