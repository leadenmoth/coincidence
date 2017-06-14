package en.haagahelia.course.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import en.haagahelia.course.domain.Poll;

//CrudRepository of Poll objects
public interface PollRepository extends CrudRepository<Poll, Long> {
	//Search function to find polls by percent used in the main controller
	List<Poll> findByPercentage(int percentage);
	List<Poll> findAllByOrderByIdDesc();
}
