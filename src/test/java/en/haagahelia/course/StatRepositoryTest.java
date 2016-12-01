package en.haagahelia.course;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import en.haagahelia.course.domain.Poll;
import en.haagahelia.course.domain.Stat;
import en.haagahelia.course.domain.StatRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StatRepositoryTest {
	//Test Stat repository for basic functions
    @Autowired
    private StatRepository repository;
    
    //Test that you can find a record by percent
    @Test
    public void findByPercentShouldReturnStat() {
        List<Stat> stats = repository.findByPercent(34);
        assertThat(stats).hasSize(3);
        assertThat(stats.get(0).getAnswer()).isEqualTo("own cats");
    }
    //Test that you can add a record to StatRepository
    @Test
    public void createNewStat() {
    	Stat stat = new Stat(86, "secretly disapprove the president", new Poll("Do you approve the president?", "RFE/RL, 2016", "Russians"));
    	repository.save(stat);
    	System.out.println(repository.findOne(stat.getId()));
    	assertThat(repository.findOne(stat.getId())).isNotNull();
    } 
    //Test that you can delete a record from the repository
    @Test
    public void deleteBook() {
        List<Stat> stats = repository.findByPercent(38);
        repository.delete(stats.get(0).getId());
        assertThat(repository.findOne(stats.get(0).getId()) == null);
        
    }
    
   

}