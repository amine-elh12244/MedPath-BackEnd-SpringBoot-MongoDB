package agil.teem.harmoniacarebackend.Controllers;

import agil.teem.harmoniacarebackend.entities.Request;
import agil.teem.harmoniacarebackend.entities.Result;

import agil.teem.harmoniacarebackend.entities.RequestStatus;
import agil.teem.harmoniacarebackend.services.RequestService;
import agil.teem.harmoniacarebackend.services.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;
    private final WebSocketService webSocketService;


    @Autowired
    public RequestController(RequestService requestService, WebSocketService webSocketService) {
        this.requestService = requestService;
        this.webSocketService = webSocketService;
    }

    // Create a new request
    @PostMapping
    public Request createRequest(@RequestBody Request request) {
        return requestService.createRequest(request);
    }

    @PostMapping("/{requestId}/results")
    public ResponseEntity<Void> addResultToRequest(@PathVariable String requestId, @RequestBody Result result) {
        requestService.addResultToRequest(requestId, result);
        webSocketService.sendMessage("request-updated");
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
        webSocketService.sendMessage("request-updated");
        return requestService.updateRequest(request);
    }

    @PatchMapping("/{id}/status")
    public Request updateRequestStatus(@PathVariable String id) {
        webSocketService.sendMessage("request-updated");
        return requestService.updateRequestStatus(id);
    }

    @PatchMapping("/{requestId}/assignCourier/{courierId}")
    public Request assignCourier(@PathVariable String requestId, @PathVariable String courierId) {
        webSocketService.sendMessage("request-updated");
        return requestService.assignCourier(requestId, courierId);
    }

    @PatchMapping("/{requestId}/receptionLabo/{labId}")
    public Request assigneLab(@PathVariable String requestId, @PathVariable String labId) {
        webSocketService.sendMessage("request-updated");
        return requestService.assignLab(requestId, labId);
    }



    // Delete a request
    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable String id) {
        webSocketService.sendMessage("request-updated");

        requestService.deleteRequest(id);
    }



    // status implemetatios
    @PatchMapping("/{id}/status/enlevementMedecin")
    public Request updateStatusToEnlevementMedecin(@PathVariable String id) {
        webSocketService.sendMessage("request-updated");

        return requestService.updateStatusToEnlevementMedecin(id);
    }

    @PatchMapping("/{id}/status/resultatLivre")
    public Request updateStatusToResultatLivre(@PathVariable String id) {
        webSocketService.sendMessage("request-updated");

        return requestService.updateStatusToResultatLivre(id);
    }

    @PatchMapping("/{id}/status/resultatReceptionnee")
    public Request updateStatusToResultatReceptionnee(@PathVariable String id) {
        webSocketService.sendMessage("request-updated");

        return requestService.updateStatusToResultatReceptionnee(id);
    }



    @PatchMapping("/{id}/status/enCoursDeTest")
    public Request updateStatusToEnCoursDeTest(@PathVariable String id) {
        webSocketService.sendMessage("request-updated");

        return requestService.updateStatusToEnCoursDeTest(id);
    }

    @PatchMapping("/{id}/status/resultatDisponible")
    public Request updateStatusToResultatDisponible(@PathVariable String id, @RequestBody Result result) {
        Request updatedRequest = requestService.updateStatusToResultatDisponible(id);
        requestService.addResultToRequest(id, result);
        webSocketService.sendMessage("request-updated");
        return updatedRequest;
    }

    @PatchMapping("/{id}/status/enlevementLabo")
    public Request updateStatusToEnlevementLabo(@PathVariable String id) {
        webSocketService.sendMessage("request-updated");
        return requestService.updateStatusToEnlevementLabo(id);
    }
}