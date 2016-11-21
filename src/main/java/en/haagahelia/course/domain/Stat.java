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
	private int percent;
	private String answer;
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "pollId")
	private Poll poll;
	
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
