package en.haagahelia.course.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Poll {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String question;
	private String source;
	private String subject;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Stat> stats;

	public Poll(String question, String source, String subject) {
		super();
		this.question = question;
		this.source = source;
		this.subject = subject;
	}
	
	public Poll() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<Stat> getStats() {
		return stats;
	}
	public void setStats(List<Stat> stats) {
		this.stats = stats;
	}

	public Poll(Long id, String question, String source, String subject) {
		super();
		this.id = id;
		this.question = question;
		this.source = source;
		this.subject = subject;
	}
	
}
