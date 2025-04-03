package ProjectAtlas;

import ProjectAtlas.Models.Forecast;
import ProjectAtlas.Models.Location;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ForecastFetcher {

    HttpClient client = HttpClient.newHttpClient();
    String baseURI = "https://api.openweathermap.org/data/2.5/weather";
    String apiKey;

    ForecastFetcher(String apikey) {
        this.apiKey = apikey;
    }
    //lat 53.4794892
    //lon -2.2451148
    //apikey 5f5b5695d5b1899e94a0af9700e89f76
    public Forecast fetchForecastForLocation(Location location) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(baseURI + String.format("?lat=%s&lon=%s&appid=%s&units=metric", location.lon, location.lat, apiKey));
        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();

        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        Forecast forecast = mapper.readValue(response.body().toString(), Forecast.class);
        forecast.location = location;
        return forecast;
    }
}
