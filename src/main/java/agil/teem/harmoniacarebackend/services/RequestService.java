package agil.teem.harmoniacarebackend.services;

import agil.teem.harmoniacarebackend.EmailSender.EmailService;
import agil.teem.harmoniacarebackend.entities.*;
import agil.teem.harmoniacarebackend.repositories.LaboratoireRepository;
import agil.teem.harmoniacarebackend.repositories.RequestRepository;
import agil.teem.harmoniacarebackend.repositories.CoursierRepository;
import agil.teem.harmoniacarebackend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final EmailService emailService;

    private final CoursierRepository  CoursierRepository;
    private final LaboratoireRepository laboratoireRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public RequestService(RequestRepository requestRepository,CoursierRepository coursierRepository , LaboratoireRepository laboratoireRepository ,EmailService emailService ,  RoleRepository roleRepository) {
        this.requestRepository = requestRepository;
        this.CoursierRepository = coursierRepository;
        this.laboratoireRepository = laboratoireRepository;
        this.emailService = emailService;
        this.roleRepository = roleRepository;
    }


    public Request assignCourier(String requestId, String courierId) {

        Optional<Request> optionalRequest = requestRepository.findById(requestId);
        Optional<Coursier> optionalCourier = CoursierRepository.findById(courierId);
        if (optionalRequest.isPresent() && optionalCourier.isPresent()) {
            Request request = optionalRequest.get();
            Coursier courier = optionalCourier.get();
            request.assignCourier(courier);
            String courierEmail = courier.getEmail(); // replace this with actual code to get the courier's email
            String subject = "You have been assigned a new request";
            String text = "You have been assigned to the request with ID: " + requestId;
            emailService.sendSimpleMessage(courierEmail, subject, text);
            return requestRepository.save(request);

        } else {
            throw new RuntimeException("Request or courier not found");
        }
        // ...
    }

    public Request assignLab(String requestId, String labId) {
        Optional<Request> optionalRequest = requestRepository.findById(requestId);
        Optional<Laboratoire> optionalLab = laboratoireRepository.findById(labId);

        if (optionalRequest.isPresent() && optionalLab.isPresent()) {
            Request request = optionalRequest.get();
            Laboratoire lab = optionalLab.get();
            request.assignLabo(lab);

            return requestRepository.save(request);
        } else {
            throw new RuntimeException("Request or lab not found");
        }
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

        // Verify and update roles for Medecin if not null
        if (request.getMedecin() != null) {
            Set<Role> medecinRoles = new HashSet<>();
            for (Role userRole : request.getMedecin().getRole()) {
                String roleName = userRole.getRoleName();
                Role existingRole = roleRepository.findByRoleName(roleName);

                if (existingRole != null) {
                    // If the role already exists, use the existing one
                    medecinRoles.add(existingRole);
                } else {
                    // If the role doesn't exist, save it in the database
                    medecinRoles.add(roleRepository.save(userRole));
                }
            }
            // Save the updated roles for Medecin
            request.getMedecin().setRole(medecinRoles);
        }

        // Verify and update roles for Lab if not null
        if (request.getLab() != null) {
            Set<Role> labRoles = new HashSet<>();
            for (Role userRole : request.getLab().getRole()) {
                String roleName = userRole.getRoleName();
                Role existingRole = roleRepository.findByRoleName(roleName);

                if (existingRole != null) {
                    // If the role already exists, use the existing one
                    labRoles.add(existingRole);
                } else {
                    // If the role doesn't exist, save it in the database
                    labRoles.add(roleRepository.save(userRole));
                }
            }
            // Save the updated roles for Lab
            request.getLab().setRole(labRoles);
        }

        // Save the updated request
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