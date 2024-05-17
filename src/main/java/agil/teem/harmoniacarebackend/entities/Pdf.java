package agil.teem.harmoniacarebackend.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.Binary;


@Document(collection = "docs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pdf {

    @Id
    private String id;
    private Binary file;

}
