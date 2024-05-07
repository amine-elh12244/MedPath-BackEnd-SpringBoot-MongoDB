package agil.teem.harmoniacarebackend.Controllers;

import agil.teem.harmoniacarebackend.entities.Request;
import agil.teem.harmoniacarebackend.entities.Result;

import agil.teem.harmoniacarebackend.entities.RequestStatus;
import agil.teem.harmoniacarebackend.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    // Create a new request
    @PostMapping
    public Request createRequest(@RequestBody Request request) {
        return requestService.createRequest(request);
    }

    @PostMapping("/{requestId}/results")
    public ResponseEntity<Void> addResultToRequest(@PathVariable String requestId, @RequestBody Result result) {
        requestService.addResultToRequest(requestId, result);
        return ResponseEntity.ok().build();
    }

    // Retrieve all requests
    @GetMapping
    public List<Request> getAllRequests() {
        return requestService.getAllRequests();
    }

    // Retrieve a request by its ID
    @GetMapping("/{id}")
    public Optional<Request> getRequestById(@PathVariable String id) {
        return requestService.getRequestById(id);
    }

    @GetMapping("/status/{status}")
    public List<Request> getRequestsByStatus(@PathVariable RequestStatus status) {
        return requestService.getRequestsByStatus(status);
    }

    // Update a request
    @PutMapping("/{id}")
    public Request updateRequest(@PathVariable String id, @RequestBody Request request) {
        request.setId(id);
        return requestService.updateRequest(request);
    }

    @PatchMapping("/{id}/status")
    public Request updateRequestStatus(@PathVariable String id) {
        return requestService.updateRequestStatus(id);
    }

    @PatchMapping("/{requestId}/courier/{courierId}")
    public Request assignCourier(@PathVariable String requestId, @PathVariable String courierId) {
        return requestService.assignCourier(requestId, courierId);
    }

    // Delete a request
    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable String id) {
        requestService.deleteRequest(id);
    }



    // status implemetatios
    @PatchMapping("/{id}/status/enlevementMedecin")
    public Request updateStatusToEnlevementMedecin(@PathVariable String id) {
        return requestService.updateStatusToEnlevementMedecin(id);
    }

    @PatchMapping("/{id}/status/resultatLivre")
    public Request updateStatusToResultatLivre(@PathVariable String id) {
        return requestService.updateStatusToResultatLivre(id);
    }

    @PatchMapping("/{id}/status/resultatReceptionnee")
    public Request updateStatusToResultatReceptionnee(@PathVariable String id) {
        return requestService.updateStatusToResultatReceptionnee(id);
    }

    @PatchMapping("/{id}/status/receptionLabo")
    public Request updateStatusToReceptionLabo(@PathVariable String id) {
        return requestService.updateStatusToReceptionLabo(id);
    }

    @PatchMapping("/{id}/status/enCoursDeTest")
    public Request updateStatusToEnCoursDeTest(@PathVariable String id) {
        return requestService.updateStatusToEnCoursDeTest(id);
    }

    @PatchMapping("/{id}/status/resultatDisponible")
    public Request updateStatusToResultatDisponible(@PathVariable String id) {
        return requestService.updateStatusToResultatDisponible(id);
    }

    @PatchMapping("/{id}/status/enlevementLabo")
    public Request updateStatusToEnlevementLabo(@PathVariable String id) {
        return requestService.updateStatusToEnlevementLabo(id);
    }
}