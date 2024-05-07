package agil.teem.harmoniacarebackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

@Document(collection = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    private String id;

    @NonNull
    @Field("nom")
    private String nom;

    @NonNull
    @Field("prenom")
    private String prenom;

    @Field("sexe")
    private String sexe;

    @Field("ville")
    private String ville;

    @Field("dateNaissance")
    private Date dateNaissance;

    @Field("telephone")
    private String telephone;

    @Field("antecedant")
    private String antecedant;

    @Field("mutuelle")
    private boolean mutuelle;

    @Field("remarque")
    private String remarque;
}