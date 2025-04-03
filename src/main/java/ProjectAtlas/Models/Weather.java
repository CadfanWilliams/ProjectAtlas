package ProjectAtlas.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Weather {
    @JsonProperty
    String id;
    @JsonProperty
    String main;
    @JsonProperty
    String description;
    @JsonProperty
    String icon;

}
