package en.haagahelia.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import en.haagahelia.course.domain.UserRepository;
import en.haagahelia.course.domain.User;

@SpringBootApplication
public class CoincidenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoincidenceApplication.class, args);
	}
	//For this demo our DB will only have 3 tables: users, polls (for simplicity, each poll only contains one question)
	//and stats (answers with percentages linked to polls by a many-to-one relationship)
	//Populate the in-memory database with CLR
	/*@Bean
	public CommandLineRunner demo(UserRepository urepository) {
		return (args) -> {

			//Passwords are 'password'
			urepository.save(new User("user", "$2a$04$38PefzLvW0TjewRTUBGlB.CAkQmxHVYgwxqT.GT3w/r6MPEV/K1Jq", "user@mail.fi", "USER"));
			urepository.save(new User("admin", "$2a$04$M0hCFefLmgOnhRkg9zr9Y.OMhCbtk.ImhVIuWCog.LOIyOw280G4O", "admin@mail.fi", "ADMIN"));
		};
	}*/

}
