package en.haagahelia.course.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Stat {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    //for this demo let's keep percentages whole. In future there should be an interface for the user to specify
    //the degree of search fuzziness, like "+/- 3.4%"
	private int percent;
	private String answer;
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "pollId")
	private Poll poll; //connecting to parent table with a many-to-one relation. One question has many answers
	
	public Stat(int percent, String answer, Poll poll) {
		super();
		this.percent = percent;
		this.answer = answer;
		this.poll = poll;
	}

	public Stat() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	@Override
	public String toString() {
		return "Stat [id=" + id + ", percent=" + percent + ", answer=" + answer + ", poll=" + poll + "]";
	}	

}
