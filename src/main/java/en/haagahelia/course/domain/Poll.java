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
	private Long categoryId;
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Stat> stats;
	public Poll(String name) {
		super();
		this.name = name;
	}
	public Poll() {
		
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Stat> getBooks() {
		return stats;
	}
	public void setBooks(List<Stat> stats) {
		this.stats = stats;
	}
	@Override
	public String toString() {
		return "Poll [categoryId=" + categoryId + ", name=" + name + "]";
	}
	
}
