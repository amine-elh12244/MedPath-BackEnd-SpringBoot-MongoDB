package agil.teem.harmoniacarebackend.repositories;

import agil.teem.harmoniacarebackend.entities.Request;
import agil.teem.harmoniacarebackend.entities.RequestStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RequestRepository extends MongoRepository<Request, String> {

    List<Request> findAllByStatus(RequestStatus status);

    List<Request> findByMedecinId(String medecinId);

    List<Request> findByMedecinIdAndStatus(String medecinId, RequestStatus status);
}