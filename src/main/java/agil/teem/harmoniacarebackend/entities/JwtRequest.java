package agil.teem.harmoniacarebackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class JwtRequest {

    private String userName;
    private String userPassword;

}
