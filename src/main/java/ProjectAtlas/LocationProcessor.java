package ProjectAtlas;

import ProjectAtlas.DTO.Location;
import ProjectAtlas.Repository.LocationRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationProcessor {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> locationsDTOList;
    public ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    public File JSONfile = new File("src/main/resources/static/world_cities_partial.json");


    public void processJSON() throws IOException {
        locationsDTOList = mapper.readValue(JSONfile, new TypeReference<List<Location>>() {
        });

        for (Location locationDTO : locationsDTOList) {
            ProjectAtlas.Models.Location location = ProjectAtlas.Models.Location.builder()
                    .name(locationDTO.name)
                    .country(locationDTO.country)
                    .lon(locationDTO.lng)
                    .lat(locationDTO.lat)
                    .build();

            locationRepository.save(location);
        }

    }

    public List<ProjectAtlas.Models.Location> processJSONIntoList(File JSONfile) throws IOException {
        List<Location> locationDTOList = mapper.readValue(JSONfile, new TypeReference<>() {
        });
        List<ProjectAtlas.Models.Location> locationModelList = new ArrayList<>();
        for (Location locationDTO : locationDTOList) {
            ProjectAtlas.Models.Location location = ProjectAtlas.Models.Location.builder()
                    .name(locationDTO.name)
                    .country(locationDTO.country)
                    .lon(locationDTO.lng)
                    .lat(locationDTO.lat)
                    .build();
            locationModelList.add(location);
        }
        return locationModelList;
    }
}
