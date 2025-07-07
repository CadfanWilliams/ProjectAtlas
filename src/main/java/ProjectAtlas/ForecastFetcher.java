package ProjectAtlas;

import ProjectAtlas.DTO.WeatherReadingResponse;
import ProjectAtlas.Models.Location;
import ProjectAtlas.Models.WeatherReading;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

public class ForecastFetcher {

    private final HttpClient client;
    private final String baseURI = "https://api.openweathermap.org/data/2.5/weather";
    private final String apiKey;

    public ForecastFetcher(String apiKey) {
        this(apiKey, HttpClient.newHttpClient());
    }

    public ForecastFetcher(String apiKey, HttpClient client) {
        this.apiKey = apiKey;
        this.client = client;
    }

    public WeatherReading fetchForecastForLocation(Location location) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI(baseURI + String.format("?lat=%s&lon=%s&appid=%s&units=metric", location.getLat(), location.getLon(), apiKey));
        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();

        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        WeatherReadingResponse weatherReading = mapper.readValue(response.body().toString(), WeatherReadingResponse.class);

        return WeatherReading.builder()
                .weatherDescription(weatherReading.getWeather()[0].getDescription())
                .location(location)
                .humidity(weatherReading.getMain().getHumidity())
                .windSpeed(weatherReading.getWind().getSpeed())
                .recordedAt(LocalDateTime.now())
                .temperature(Float.valueOf(weatherReading.getMain().getTemp()))
                .build();
    }
}
