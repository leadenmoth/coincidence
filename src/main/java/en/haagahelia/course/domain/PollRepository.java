package en.haagahelia.course.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import en.haagahelia.course.domain.Poll;

public interface PollRepository extends CrudRepository<Poll, Long>{
	List<Poll> findByName(String name);

}
