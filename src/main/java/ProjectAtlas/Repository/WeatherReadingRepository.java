package ProjectAtlas.Repository;

import ProjectAtlas.Models.WeatherReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherReadingRepository extends JpaRepository<WeatherReading, Long> {
    List<WeatherReading> findByLocationIdOrderByRecordedAtDesc(Long locationId);
}
