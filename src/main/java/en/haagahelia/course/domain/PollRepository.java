package en.haagahelia.course.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import en.haagahelia.course.domain.Poll;
//Create a CrudRepository of Poll objects
public interface PollRepository extends CrudRepository<Poll, Long>{
	//Search function to find polls by their subject
	List<Poll> findBySubject(String subject);

}
