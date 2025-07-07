package ProjectAtlas;

import ProjectAtlas.Models.Location;
import ProjectAtlas.Models.WeatherReading;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;L
import static org.mockito.Mockito.when;

public class ForecastFetcherTest {

    private HttpClient mockHttpClient;
    private ForecastFetcher forecastFetcher;
    private HttpResponse<String> mockResponse;

    @BeforeEach
    public void setUp() throws Exception {
        mockHttpClient = mock(HttpClient.class);
        mockResponse = mock(HttpResponse.class);

        String mockJsonResponse = """
        {
          "weather": [
            { "description": "clear sky" }
          ],
          "main": {
            "temp": 25.5,
            "humidity": 65
          },
          "wind": {
            "speed": 5.5
          }
        }
        """;

        when(mockResponse.body()).thenReturn(mockJsonResponse);
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        forecastFetcher = new ForecastFetcher("test-api-key", mockHttpClient);
    }

    @Test
    public void testFetchForecastForLocation() throws Exception {
        Location location = Location.builder()
                .lat("40.7128")
                .lon("-74.0060")
                .name("New York")
                .country("USA")
                .build();

        WeatherReading result = forecastFetcher.fetchForecastForLocation(location);

        assertNotNull(result);
        assertEquals("clear sky", result.getWeatherDescription());
        assertEquals(65, result.getHumidity());
        assertEquals(5.5f, result.getWindSpeed());
        assertEquals(25.5f, result.getTemperature());
        assertEquals("New York", result.getLocation().getName());
    }
}
