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
    public void findByPercentShouldReturnStat() {
        List<Poll> stats = repository.findByPercentage(34);
        assertThat(stats).hasSize(3);
        assertThat(stats.get(0).getAnswer()).isEqualTo("own cats");
    }
    //Test that you can add a record to StatRepository
    @Test
    public void createNewStat() {
    	Poll poll = new Poll(86, "secretly disapprove the president", 2017);
    	repository.save(poll);
    	System.out.println(repository.findOne(poll.getId()));
    	assertThat(repository.findOne(poll.getId())).isNotNull();
    } 
    //Test that you can delete a record from the repository
    @Test
    public void deleteBook() {
        List<Poll> poll = repository.findByPercentage(38);
        repository.delete(poll.get(0).getId());
        assertThat(repository.findOne(poll.get(0).getId()) == null);
        
    }
    
   

}