package agil.teem.harmoniacarebackend.Controllers;

import agil.teem.harmoniacarebackend.entities.Result;
import agil.teem.harmoniacarebackend.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/results")
public class ResultController {

    private final ResultService resultService;


    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    // Create a new result
    @PostMapping
    public Result createResult(@RequestBody Result result) {
        return resultService.createResult(result);
    }


    // Retrieve all results
    @GetMapping
    public List<Result> getAllResults() {
        return resultService.getAllResults();
    }

    // Retrieve a result by its ID
    @GetMapping("/{id}")
    public Optional<Result> getResultById(@PathVariable String id) {
        return resultService.getResultById(id);
    }

    // Update a result
    @PutMapping("/{id}")
    public Result updateResult(@PathVariable String id, @RequestBody Result result) {
        // You might want to set the ID of the Result object to the ID in the path
        // to ensure they are the same
        result.setId(id);
        return resultService.updateResult(result);
    }

    // Delete a result
    @DeleteMapping("/{id}")
    public void deleteResult(@PathVariable String id) {
        resultService.deleteResult(id);
    }
}