package book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
	@Id @GeneratedValue private int id;
    @Column(unique = true) private String isbn;
    @Column(name = "title") private String title;
	
	public Book(){
		
	}
	
	public Book(int id, String isbn, String title){
		this.id=id;
		this.isbn=isbn;
		this.title=title;
	}
	
	public void setBookID(int id){
		this.id=id;
	}
	
	public int getBookID(){
		return this.id;
	}
	
	public void setBookISBN(String isbn){
		this.isbn=isbn;
	}
	
	public String getBookISBN(){
		return this.isbn;
	}
	
	public void setBookTitle(String title){
		this.title=title;
	}
	public String getBookTitle(){
		return this.title;
	}

}
