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
	private String author;
	private String title;
	private int year;
	private String isbn;
	private double price;
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "categoryId")
	private Poll poll;
	
	public Stat(String author, String title, int year, String isbn, double price, Poll poll) {
		super();
		this.author = author;
		this.title = title;
		this.year = year;
		this.isbn = isbn;
		this.price = price;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Poll getCategory() {
		return poll;
	}
	public void setCategory(Poll poll) {
		this.poll = poll;
	}

	@Override
	public String toString() {
		return "Stat [id=" + id + ", title=" + title + ", author=" + author + ", year=" + year + ", isbn=" + isbn + ", price=" + price
				+ "]";
	}
	

}
