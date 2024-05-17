package agil.teem.harmoniacarebackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.Date;

@Document(collection = "tests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @Id
    private String id;

    @NonNull
    private Date datePrelevement;
    @NonNull
    private String testType;
    @NonNull
    private String sampleType;
    @NonNull
    private String labInstructions;
    @NonNull
    private String testReason;
}