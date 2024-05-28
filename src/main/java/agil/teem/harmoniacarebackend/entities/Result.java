package agil.teem.harmoniacarebackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "results")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @Id
    private String id;

    @NonNull
    private String resultData;

    @DBRef
    private Pdf resultats;


}