package en.haagahelia.course.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import en.haagahelia.course.domain.Stat;
//CrudRepository of Stat objects
public interface StatRepository extends CrudRepository<Stat, Long> {
	//Search function to find stats by percent used in the main controller
	List<Stat> findByPercent(int percent);
}
