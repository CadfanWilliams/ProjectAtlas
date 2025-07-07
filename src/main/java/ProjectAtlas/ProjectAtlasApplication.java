package ProjectAtlas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@Configuration
@SpringBootApplication()
@EnableScheduling
public class ProjectAtlasApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ProjectAtlasApplication.class, args);
    }
}
