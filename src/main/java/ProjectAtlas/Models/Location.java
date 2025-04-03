package ProjectAtlas.Models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Location {
    public String name;
    public String lat;
    public String lon;
}
