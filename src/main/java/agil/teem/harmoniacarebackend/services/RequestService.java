package agil.teem.harmoniacarebackend.services;

import agil.teem.harmoniacarebackend.entities.Coursier;
import agil.teem.harmoniacarebackend.entities.Request;
import agil.teem.harmoniacarebackend.entities.RequestStatus;
import agil.teem.harmoniacarebackend.entities.Result;
import agil.teem.harmoniacarebackend.repositories.RequestRepository;
import agil.teem.harmoniacarebackend.repositories.CoursierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    private final CoursierRepository  CoursierRepository;


    @Autowired
    public RequestService(RequestRepository requestRepository, agil.teem.harmoniacarebackend.repositories.CoursierRepository coursierRepository) {
        this.requestRepository = requestRepository;
        this.CoursierRepository = coursierRepository;
    }


    public Request assignCourier(String requestId, String courierId) {

        Optional<Request> optionalRequest = requestRepository.findById(requestId);
        Optional<Coursier> optionalCourier = CoursierRepository.findById(courierId);
        if (optionalRequest.isPresent() && optionalCourier.isPresent()) {
            Request request = optionalRequest.get();
            Coursier courier = optionalCourier.get();
            request.assignCourier(courier);
            return requestRepository.save(request);
        } else {
            throw new RuntimeException("Request or courier not found");
        }
        // ...
    }

    public void addResultToRequest(String requestId, Result result) {
        Request request = requestRepository.findById(requestId).orElseThrow(() -> new RuntimeException("Request not found"));
        request.setResult(result);
        request.setStatus(RequestStatus.RÉSULTAT_DISPONIBLE);
        requestRepository.save(request);
    }


    public Request updateStatusToEnlevementMedecin(String id) {
        return updateRequestStatus(id, RequestStatus.ENLÈVEMENT_MÉDECIN);
    }


    public Request updateStatusToResultatLivre(String id) {
        return updateRequestStatus(id, RequestStatus.RÉSULTAT_LIVRÉ);
    }


    public Request updateStatusToResultatReceptionnee(String id) {
        return updateRequestStatus(id, RequestStatus.RÉSULTAT_RÉCEPTIONNÉE);
    }


    public Request updateStatusToReceptionLabo(String id) {
        return updateRequestStatus(id, RequestStatus.RÉCEPTION_LABO);
    }


    public Request updateStatusToEnCoursDeTest(String id) {
        return updateRequestStatus(id, RequestStatus.EN_COURS_DE_TEST);
    }


    public Request updateStatusToResultatDisponible(String id) {
        return updateRequestStatus(id, RequestStatus.RÉSULTAT_DISPONIBLE);
    }


    public Request updateStatusToEnlevementLabo(String id) {
        return updateRequestStatus(id, RequestStatus.ENLÈVEMENT_LABO);
    }

    private Request updateRequestStatus(String id, RequestStatus status) {
        Optional<Request> optionalRequest = requestRepository.findById(id);
        if (optionalRequest.isPresent()) {
            Request request = optionalRequest.get();
            request.setStatus(status);
            return requestRepository.save(request);
        } else {
            throw new RuntimeException("Request not found");
        }
    }

    // Create a new request
    public Request createRequest(Request request) {
        request.setStatus(RequestStatus.DEMANDE_INITIEE);
        return requestRepository.save(request);
    }

    // Retrieve all requests
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    // Retrieve a request by its ID
    public Optional<Request> getRequestById(String id) {
        return requestRepository.findById(id);
    }

    // Update a request
    public Request updateRequest(Request request) {
        return requestRepository.save(request);
    }

    // Delete a request
    public void deleteRequest(String id) {
        requestRepository.deleteById(id);
    }

    // Retrieve all requests with a specific status
    public List<Request> getRequestsByStatus(RequestStatus status) {
        return requestRepository.findAllByStatus(status);
    }

    public Request updateRequestStatus(String id) {
        Optional<Request> optionalRequest = requestRepository.findById(id);
        if (optionalRequest.isPresent()) {
            Request request = optionalRequest.get();
            int nextStatusId = request.getStatus().getId() + 1;
            RequestStatus nextStatus = RequestStatus.getById(nextStatusId);
            request.setStatus(nextStatus);
            return requestRepository.save(request);
        } else {
            throw new RuntimeException("Request not found");
        }
    }


}