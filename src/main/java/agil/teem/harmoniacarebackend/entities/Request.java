package agil.teem.harmoniacarebackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "requests")
@Data
@AllArgsConstructor
public class Request {
    @Id
    private String id;


    @NonNull
    @DBRef
    private Patient patient;

    @NonNull
    @DBRef
    private Medecin medecin;

    @NonNull
    @DBRef
    private Test test;

    @NonNull
    private RequestStatus status;

    @DBRef
    private Coursier courier;

    @DBRef
    private Laboratoire lab;

    @DBRef
    private Result result;

    @DBRef
    private Pdf ordonnance;

    @DBRef
    private Pdf compteRenduAnatomopathologique;

    private LocalDate date;

    public Request() {
        this.date = LocalDate.now();
    }

    public void assignCourier(Coursier courier) {
        this.courier = courier;
        this.status = RequestStatus.DEMANDE_ASSIGNÉE;
    }

    public void assignLabo(Laboratoire lab) {
        this.lab = lab;
        this.status = RequestStatus.RÉCEPTION_LABO;
    }

//    public void addResult(Result result) {
//        this.result = result;
//        this.status = RequestStatus.RÉSULTAT_DISPONIBLE;
//    }

}