package ProjectAtlas.Models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Forecast {
    @JsonProperty("weather")
    public Weather[] weather;

    public Location location;

    public String getForecast(){
        return String.format("Todays weather in %s will be %s", location.name, weather[0].getMain().toLowerCase());
    }
}

