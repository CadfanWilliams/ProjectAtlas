package ProjectAtlas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WeatherReadingFetcherTest {


    @Test
    void givenLocationIsGivenWithAllDetails_ThenShouldReturnForecast(){
        //given
        ForecastFetcher fetcher = new ForecastFetcher("test");

        //when

        //Then
    }
}