package agil.teem.harmoniacarebackend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class MyConfiguration {

    private final RoleReaderConverter roleReaderConverter;

    public MyConfiguration(RoleReaderConverter roleReaderConverter) {
        this.roleReaderConverter = roleReaderConverter;
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        System.out.println("CORS configuration is being loaded");
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "PATCH","DELETE", "HEAD", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }

    @Bean
    public CascadeSaveMongoEventListener cascadingMongoEventListener() {
        System.out.println("CascadeSaveMongoEventListener configuration is being loaded"); // This line is added
        return new CascadeSaveMongoEventListener();
    }

    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(roleReaderConverter));
    }


}