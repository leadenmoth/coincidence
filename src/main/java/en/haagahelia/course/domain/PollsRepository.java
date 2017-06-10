package en.haagahelia.course.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import en.haagahelia.course.domain.Polls;

//CrudRepository of Polls objects
public interface PollsRepository extends CrudRepository<Polls, Long> {
	//Search function to find polls by percent used in the main controller
	List<Polls> findByPercentage(int percentage);
}
