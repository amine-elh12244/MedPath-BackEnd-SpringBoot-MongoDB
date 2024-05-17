package agil.teem.harmoniacarebackend.repositories;

import agil.teem.harmoniacarebackend.entities.Pdf;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PdfRepository extends MongoRepository<Pdf, String> {

}
