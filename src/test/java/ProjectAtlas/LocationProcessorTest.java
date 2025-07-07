package ProjectAtlas;

import ProjectAtlas.Repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LocationProcessorTest {

    @InjectMocks
    private LocationProcessor locationProcessor;

    @Mock
    private LocationRepository locationRepository;

    private File testJsonFile;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Prepare test JSON file
        String testJsonContent = """
        [
            {
                "name": "New York",
                "country": "USA",
                "lat": 40.7128,
                "lng": -74.0060
            },
            {
                "name": "London",
                "country": "UK",
                "lat": 51.5074,
                "lng": -0.1278
            }
        ]
        """;

        testJsonFile = File.createTempFile("test_cities", ".json");
        FileWriter writer = new FileWriter(testJsonFile);
        writer.write(testJsonContent);
        writer.close();
    }

    @Test
    public void testProcessJSONIntoList() throws Exception {
        List<ProjectAtlas.Models.Location> locations = locationProcessor.processJSONIntoList(testJsonFile);

        assertEquals(2, locations.size());
        assertEquals("New York", locations.get(0).getName());
        assertEquals("UK", locations.get(1).getCountry());
    }

    @Test
    public void testProcessJSON_savesToRepository() throws Exception {
        // Override the default file with test file
        locationProcessor.JSONfile = testJsonFile;

        locationProcessor.processJSON();

        // Verify the repository's save method was called twice
        verify(locationRepository, times(2)).save(any(ProjectAtlas.Models.Location.class));
    }
}
