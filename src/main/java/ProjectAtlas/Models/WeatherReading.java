package ProjectAtlas.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "weather_readings")
@Data
public class WeatherReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Float temperature;
    public Float humidity;
    public Float windSpeed;
    public String weatherDescription;

    public LocalDateTime recordedAt;

    @ManyToOne
    @JoinColumn(name = "location_id")
    public Location location;

    public String getWeatherReading(){
        return String.format(" --- Todays weather in %s --- \n Temperature: %s°c \n Humidity: %s \n Wind Speed: %sMPH \n Weather Description: %s \n recorded at: %s",location.getName(), temperature, humidity, windSpeed, weatherDescription, recordedAt);
    }
}

