package ProjectAtlas.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class Location {
    public String country;
    public String name;
    public String lat;
    public String lng;
}
