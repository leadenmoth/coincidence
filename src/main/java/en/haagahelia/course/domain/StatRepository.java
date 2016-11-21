	package en.haagahelia.course.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import en.haagahelia.course.domain.Stat;

public interface StatRepository extends CrudRepository<Stat, Long> {
	List<Stat> findByAuthor(String author);
	List<Stat> findByYear(int year);
}
