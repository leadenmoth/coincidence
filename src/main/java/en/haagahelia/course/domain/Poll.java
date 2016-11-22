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
	/*
	This is essentially a stub for a Poll object/repository.
	Eventually it will contain many fields to serve as search tags.
	Ultimately user should be able to limit search to something like
	"Moscovites 18-25, pet owners, polled between 2011 and 2014"
	For this demo we only have a free-form question field plus source and one-word subject ("who?")
	*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pollId; //auto-generated id to simplify add/remove operations
	private String question;
	private String source;
	private String subject;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "poll")
	private List<Stat> stats; //linking to dependent Stats table

	public Poll(String question, String source, String subject) {
		super();
		this.question = question;
		this.source = source;
		this.subject = subject;
	}
	
	public Poll() {
		
	}
	
	public Long getPollId() {
		return pollId;
	}

	public void setPollId(Long pollId) {
		this.pollId = pollId;
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

	@Override
	public String toString() {
		return "Poll [pollId=" + pollId + ", question=" + question + ", source=" + source + ", subject=" + subject + ", stats="
				+ stats + "]";
	}

	
}
