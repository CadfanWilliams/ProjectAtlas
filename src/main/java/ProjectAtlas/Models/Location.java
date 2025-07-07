package ProjectAtlas.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;
    private String timezone;
    private String lon;
    private String lat;

//    @Column(columnDefinition = "GEOGRAPHY(Point, 4326)")
//    private String coordinates; // Store as WKT or use PostGIS library

//    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
//    private List<WeatherReading> weatherReadings = new ArrayList<>();

}
