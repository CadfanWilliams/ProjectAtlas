package ProjectAtlas;

import ProjectAtlas.Models.Location;
import ProjectAtlas.Repository.LocationRepository;
import ProjectAtlas.Repository.WeatherReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class WeatherUpdateService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private WeatherReadingRepository weatherReadingRepository;

    //@Scheduled(fixedRate = 300000)
    public void runJob() throws URISyntaxException, IOException, InterruptedException {
        List<Location> locations = locationRepository.findAll();
        ForecastFetcher fetcher = new ForecastFetcher("5f5b5695d5b1899e94a0af9700e89f76");

        for (Location location : locations) {
            System.out.println(fetcher.fetchForecastForLocation(location));
            weatherReadingRepository.save(fetcher.fetchForecastForLocation(location));
        }
    }

    @Scheduled(fixedRate = 500000)
    public void runJob_TEST() throws URISyntaxException, IOException, InterruptedException {
        File JSONfile = new File("src/main/resources/static/test_cities.json");
        LocationProcessor processor = new LocationProcessor();
        List<Location> locations =  processor.processJSONIntoList(JSONfile);

        ForecastFetcher fetcher = new ForecastFetcher("5f5b5695d5b1899e94a0af9700e89f76");

        for (Location location : locations) {
            //System.out.println(fetcher.fetchForecastForLocation(location));
            fetcher.fetchForecastForLocation(location);
            //weatherReadingRepository.save(fetcher.fetchForecastForLocation(location));
        }
    }
}
