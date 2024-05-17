package agil.teem.harmoniacarebackend.services;

import agil.teem.harmoniacarebackend.EmailSender.EmailService;
import agil.teem.harmoniacarebackend.entities.*;
import agil.teem.harmoniacarebackend.repositories.LaboratoireRepository;
import agil.teem.harmoniacarebackend.repositories.MedecinRepository;
import agil.teem.harmoniacarebackend.repositories.RequestRepository;
import agil.teem.harmoniacarebackend.repositories.CoursierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final EmailService emailService;
    private final CoursierRepository  CoursierRepository;
    private final LaboratoireRepository laboratoireRepository;
    private final MedecinService medecinService; // Add this line
    private final LaboratoireService laboService; // Add this line
    private final PdfService pdfService;


    @Autowired
    public RequestService(RequestRepository requestRepository, CoursierRepository coursierRepository, LaboratoireRepository laboratoireRepository, EmailService emailService, MedecinService medecinService, LaboratoireService laboService, PdfService pdfService) { // Update this line
        this.requestRepository = requestRepository;
        this.CoursierRepository = coursierRepository;
        this.laboratoireRepository = laboratoireRepository;
        this.emailService = emailService;
        this.medecinService = medecinService; // Add this line
        this.laboService = laboService; // Add this line
        this.pdfService = pdfService;
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
//    public Request createRequest(Request request) {
//        request.setStatus(RequestStatus.DEMANDE_INITIEE);
//        // Create a new medecin and save it
//
//        Medecin savedMedecin = null;
//        if (request.getMedecin() != null) {
//            savedMedecin = medecinService.createMedecin(request.getMedecin());
//        }
//        // Create a new labo and save it if it's not null
//        Laboratoire savedLabo = null;
//        if (request.getLab() != null) {
//            savedLabo = laboService.createLaboratoire(request.getLab());
//        }
//
//        // Set saved medecin and labo in the request
//        request.setMedecin(savedMedecin);
//        request.setLab(savedLabo);
//
//        // Save the request
//        return requestRepository.save(request);
//    }


    public Request createRequest(Request request) {
        request.setStatus(RequestStatus.DEMANDE_INITIEE);
        Optional<Medecin> existingMedecinOptional = medecinService.getMedecinById(request.getMedecin().getId());
        if (!existingMedecinOptional.isPresent()) {
            throw new RuntimeException("Medecin not found");
        }
        request.setMedecin(existingMedecinOptional.get());

        if (request.getOrdonnance() != null) {
            Optional<Pdf> Ordonnance = pdfService.getpdfById(request.getOrdonnance().getId());
            if (!Ordonnance.isPresent()) {
                throw new RuntimeException("Ordonnance not found");
            }
            request.setOrdonnance(Ordonnance.get());
        }

        if (request.getCompteRenduAnatomopathologique() != null) {
            Optional<Pdf> CompteRenduAnatomopathologique = pdfService.getpdfById(request.getCompteRenduAnatomopathologique().getId());
            if (!CompteRenduAnatomopathologique.isPresent()) {
                throw new RuntimeException("CompteRenduAnatomopathologique not found");
            }
            request.setOrdonnance(CompteRenduAnatomopathologique.get());
        }

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

    public List<Request> getRequestsByDoctor(String doctorId) {
        return requestRepository.findByMedecinId(doctorId);
    }


    public List<Request> getRequestsByDoctorAndStatus(String doctorId, RequestStatus status) {
        return requestRepository.findByMedecinIdAndStatus(doctorId, status);
    }


}