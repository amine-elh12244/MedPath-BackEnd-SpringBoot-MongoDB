package agil.teem.harmoniacarebackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medecin extends user {
    @NonNull
    @Field("QuotaVouchers")
    private int QuotaVouchers;

    @Field("Remarque")
    private String Remarque;

    @DBRef
    @Field("requests")
    private List<Request> requests = new ArrayList<>();
}