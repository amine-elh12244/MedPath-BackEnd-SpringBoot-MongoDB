package agil.teem.harmoniacarebackend.Controllers;

import agil.teem.harmoniacarebackend.entities.Test;
import agil.teem.harmoniacarebackend.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tests")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    // Create a new test
    @PostMapping
    public Test createTest(@RequestBody Test test) {
        return testService.createTest(test);
    }

    // Retrieve all tests
    @GetMapping
    public List<Test> getAllTests() {
        return testService.getAllTests();
    }

    // Retrieve a test by its ID
    @GetMapping("/{id}")
    public Optional<Test> getTestById(@PathVariable String id) {
        return testService.getTestById(id);
    }

    // Update a test
    @PutMapping("/{id}")
    public Test updateTest(@PathVariable String id, @RequestBody Test test) {
        // You might want to set the ID of the Test object to the ID in the path
        // to ensure they are the same
        test.setId(id);
        return testService.updateTest(test);
    }

    // Delete a test
    @DeleteMapping("/{id}")
    public void deleteTest(@PathVariable String id) {
        testService.deleteTest(id);
    }
}