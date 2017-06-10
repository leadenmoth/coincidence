package en.haagahelia.course.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Polls object stores a single response to a question in a corresponding Poll
@Entity
public class Polls {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    //for this demo let's keep percentages whole. In future there should be an interface for the user to specify
    //the degree of search fuzziness, like "+/- 3.4%"
    private int percentage;
    private String answer;
    private int year;
	
		
	public Polls(int percentage, String answer, int year) {
		super();
		this.percentage = percentage;
		this.answer = answer;		
		this.year = year;
	}

	public Polls() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percent) {
		this.percentage = percent;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Stat [id=" + id + ", percent=" + percentage + ", answer=" + answer + ", year=" + year + "]";
	}	

}
