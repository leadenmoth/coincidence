package en.haagahelia.course;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import en.haagahelia.course.domain.Poll;
import en.haagahelia.course.domain.PollRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PollRepositoryTest {
	//Test Poll repository for basic functions
    @Autowired
    private PollRepository repository;
    
    //Test that you can find a record by percent
    @Test
    public void findBySubjectShouldReturnPoll() {
        List<Poll> polls = repository.findBySubject("Russians");
        assertThat(polls).hasSize(3);
        assertThat(polls.get(0).getSource()).isEqualTo("Bloomberg");
    }
    //Test that you can add a record to StatRepository
    @Test
    public void createNewPoll() {
    	Poll poll = new Poll("Do you approve the president?", "RFE/RL, 2016", "Russians");
    	repository.save(poll);
    	System.out.println(repository.findOne(poll.getPollId()));
    	assertThat(repository.findOne(poll.getPollId())).isNotNull();
    } 
    //Test that you can delete a record from the repository
    @Test
    public void deleteBook() {
        List<Poll> polls = repository.findBySubject("Russians");
        repository.delete(polls.get(0).getPollId());
        assertThat(repository.findOne(polls.get(0).getPollId()) == null);
        
    }
    
   

}