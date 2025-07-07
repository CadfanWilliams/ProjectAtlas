package ProjectAtlas.DTO;

import lombok.Data;
//This is the Data Transfer Object for grapping the weather forecast from weather API
@Data
public class WeatherReadingResponse {
    //{"coord":{"lon":51.5085,"lat":-0.1257},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds",
    // "icon":"04n"}],"base":"stations","main":{"temp":27.91,"feels_like":31.16,"temp_min":27.91,
    // "temp_max":27.91,"pressure":1012,"humidity":75,"sea_level":1012,"grnd_level":1012},
    // "visibility":10000,"wind":{"speed":7.62,"deg":177,"gust":7.79},"clouds":{"all":100},
    // "dt":1748732525,"sys":{"sunrise":1748744915,"sunset":1748788521},"timezone":10800,"id":0,"name":"","cod":200}

    public Coord coordinates;
    public Weather[] weather;
    public String base;
    public Main main;
    public Wind wind;
}

