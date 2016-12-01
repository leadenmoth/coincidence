package en.haagahelia.course;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import en.haagahelia.course.web.StatController;
//Standard test to see if all controllers load ok
@RunWith(SpringRunner.class)
@SpringBootTest
public class CoincidenceApplicationTests {
	@Autowired StatController controller;
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
