package agil.teem.harmoniacarebackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "coursiers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coursier {
    @Id
    private String id;

    @NonNull
    @Field("nom")
    private String nom;

    @NonNull
    @Field("prenom")
    private String prenom;

    @NonNull
    @Indexed(unique = true)
    @Field("email")
    private String email;

    @Field("adresse")
    private String adresse;

    @Field("telephone")
    private String telephone;

    @DBRef
    @Field("requests")
    private List<Request> requests = new ArrayList<>();
}