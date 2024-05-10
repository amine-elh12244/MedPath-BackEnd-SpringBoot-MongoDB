package agil.teem.harmoniacarebackend.EmailSender;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("mail")
public record EmailProperties(
        //hdhdhdhdhdhhdhd
        String host,
        Integer port,
        String username,
        String password) {
}