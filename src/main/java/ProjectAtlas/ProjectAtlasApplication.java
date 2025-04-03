package ProjectAtlas;

import ProjectAtlas.Models.Forecast;
import ProjectAtlas.Models.Location;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class ProjectAtlasApplication {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        SpringApplication.run(ProjectAtlasApplication.class, args);
        //lon 53.4794892
        //lat -2.2451148
        Location manchester = Location.builder().lon("53.4794892").lat("-2.2451148").name("Manchester").build();


        ForecastFetcher fetcher = new ForecastFetcher("5f5b5695d5b1899e94a0af9700e89f76");
        Forecast todaysforecast =  fetcher.fetchForecastForLocation(manchester);
        System.out.println(todaysforecast.getForecast());
    }
}
