package agil.teem.harmoniacarebackend.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "Sessions")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Session {

    @Id
    private String id;
    private String token;
    private Instant createdAt;
    private Instant lastRefreshedAt;
    private Instant expiresAt;
    @DBRef
    private user user;


}