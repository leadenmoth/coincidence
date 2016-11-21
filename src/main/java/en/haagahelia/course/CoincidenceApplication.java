package en.haagahelia.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import en.haagahelia.course.domain.Stat;
import en.haagahelia.course.domain.StatRepository;
import en.haagahelia.course.domain.Poll;
import en.haagahelia.course.domain.PollRepository;
import en.haagahelia.course.domain.UserRepository;
import en.haagahelia.course.domain.User;

@SpringBootApplication
public class CoincidenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoincidenceApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(StatRepository repository, PollRepository crepository, UserRepository urepository) {
		return (args) -> {
			/*crepository.save(new Poll("SciFi"));
			crepository.save(new Poll("Documentary"));
			crepository.save(new Poll("Mystery"));
			crepository.save(new Poll("Thriller"));
			
			repository.save(new Stat("Douglas Adams", "The Hitchhiker's Guide to the Galaxy", 1979, "0-330-25864-8", 9.99, crepository.findByName("SciFi").get(0)));
			repository.save(new Stat("Douglas Adams", "The Restaurant at the End of the Universe", 1980, "0-345-39181-0", 9.99, crepository.findByName("SciFi").get(0)));
			repository.save(new Stat("Douglas Adams", "Life, the Universe and Everything", 1982, "0-330-26738-8", 9.99, crepository.findByName("SciFi").get(0)));
			repository.save(new Stat("Douglas Adams", "So Long, and Thanks for All the Fish", 1984, "0-330-28700-1", 9.99, crepository.findByName("SciFi").get(0)));
			repository.save(new Stat("Douglas Adams", "Mostly Harmless", 1992, "0-330-32311-3", 9.99, crepository.findByName("SciFi").get(0)));
			repository.save(new Stat("Eoin Colfer", "And Another Thing...", 2009, "978-1-4013-2358-5", 9.99, crepository.findByName("SciFi").get(0)));
			*/
			//Passwords are 'password'
			urepository.save(new User("user", "$2a$04$38PefzLvW0TjewRTUBGlB.CAkQmxHVYgwxqT.GT3w/r6MPEV/K1Jq", "user@mail.fi", "USER"));
			urepository.save(new User("admin", "$2a$04$M0hCFefLmgOnhRkg9zr9Y.OMhCbtk.ImhVIuWCog.LOIyOw280G4O", "admin@mail.fi", "ADMIN"));
		};
	}

}
