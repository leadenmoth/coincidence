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
public class BookRepositoryTest {

    @Autowired
    private StatRepository repository;
   
    @Test
    public void findByAuthorShouldReturnBook() {
        List<Stat> stats = repository.findByAuthor("Eoin Colfer");
    	//List<Stat> books = repository.findByYear(1982);
        assertThat(stats).hasSize(1);
        assertThat(stats.get(0).getTitle()).isEqualTo("And Another Thing...");
    }
    
    @Test
    public void createNewBook() {
    	Stat stat = new Stat("Mickey Mouse", "Walt Disney", 1928, "98764325405", 0.99, new Poll("Script"));
    	repository.save(stat);
    	assertThat(repository.findOne(stat.getId())).isNotNull();
    } 
    
    @Test
    public void deleteBook() {
        List<Stat> stats = repository.findByAuthor("Douglas Adams");
        repository.delete(stats.get(0).getId());
        //System.out.println(books.get(0).toString());
        assertThat(repository.findOne(stats.get(0).getId()) == null);
        
    }
    
   

}