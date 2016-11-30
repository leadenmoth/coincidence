package en.haagahelia.course.domain;

import org.springframework.data.repository.CrudRepository;
//CrudRepository of User objects
public interface UserRepository extends CrudRepository<User, Long> {
	//Search function to find users by username
	User findByUsername(String username);
}