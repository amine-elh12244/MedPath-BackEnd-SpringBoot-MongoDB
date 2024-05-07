package agil.teem.harmoniacarebackend.services;

import agil.teem.harmoniacarebackend.entities.Result;
import agil.teem.harmoniacarebackend.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    private final ResultRepository resultRepository;

    @Autowired
    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    // Create a new result
    public Result createResult(Result result) {
        return resultRepository.save(result);
    }

    // Retrieve all results
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    // Retrieve a result by its ID
    public Optional<Result> getResultById(String id) {
        return resultRepository.findById(id);
    }

    // Update a result
    public Result updateResult(Result result) {
        return resultRepository.save(result);
    }

    // Delete a result
    public void deleteResult(String id) {
        resultRepository.deleteById(id);
    }
}