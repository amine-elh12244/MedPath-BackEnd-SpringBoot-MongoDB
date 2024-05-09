package agil.teem.harmoniacarebackend;

import agil.teem.harmoniacarebackend.EmailSender.EmailProperties;
import agil.teem.harmoniacarebackend.entities.user;
import agil.teem.harmoniacarebackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import agil.teem.harmoniacarebackend.services.MedecinService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


import java.util.List;

@EnableConfigurationProperties(EmailProperties.class)

@SpringBootApplication
public class HarmoniaCareBackEndApplication implements CommandLineRunner {


    @Autowired
    private MedecinService MedecinService;

    @Autowired
    private UserRepository UserRepository;


    public static void main(String[] args) {
        SpringApplication.run(HarmoniaCareBackEndApplication.class, args);
    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Override
    public void run(String... args) throws Exception {


        String roleId = "6637cf174b0ff77065ea1a53";

        // Retrieve users with the specified role ID
        List<user> users = UserRepository.findByRoleId(roleId);

        // Assert that the list of users is not null and not empty
        //assertThat(users).isNotNull();
       // assertThat(users).isNotEmpty();

        // Print the list of users for inspection
        System.out.println(users);
        users.forEach(System.out::println);
       // Medecin medecin = new Medecin();
        //medecin.setNom("Dr. John");
        //medecin.setPrenom("Doe");
        //medecin.setEmail("johndoe@example.com");
        //medecin.setAdresse("123 Street, City, Country");
        //medecin.setTelephone("1234567890");
        //medecin.setPassword("password");
        //medecin.setQuotaVouchers(10);
        //medecin.setRole("medecin");
        //medecin.setRemarque("This is a remark.");
        //medecin.setRequests(new ArrayList<>());

        //medecin = MedecinService.createMedecin(medecin);
        //System.out.println("Medecin has been created successfully: " + medecin);

    }
}
