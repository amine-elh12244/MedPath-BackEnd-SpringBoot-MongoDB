package agil.teem.harmoniacarebackend.services;

import agil.teem.harmoniacarebackend.entities.Medecin;
import agil.teem.harmoniacarebackend.entities.Pdf;
import agil.teem.harmoniacarebackend.repositories.PdfRepository;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class PdfService {

    @Autowired
    private PdfRepository pdfRepository;

    public String store(MultipartFile file) {
        try {
            Binary pdfFile = new Binary(file.getBytes());
            Pdf pdf = new Pdf();
            pdf.setFile(pdfFile);
            pdf = pdfRepository.save(pdf);
            return pdf.getId();
        } catch (Exception e) {
            throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    public Pdf getFile(String id) {
        return pdfRepository.findById(id).orElseThrow(() -> new RuntimeException("File not found with id " + id));
    }

    public Optional<Pdf> getpdfById(String id) {
        return pdfRepository.findById(id);
    }
}
