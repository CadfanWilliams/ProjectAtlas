package ProjectAtlas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProjectAtlasApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void purposefully_fail() {
		assertEquals("test", "testtest");
	}

}
