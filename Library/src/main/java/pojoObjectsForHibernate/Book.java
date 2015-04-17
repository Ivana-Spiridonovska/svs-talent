package pojoObjectsForHibernate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "book")
@DiscriminatorValue("book")
public class Book extends Publication{
    @Column(unique = true, name = "isbn") private String isbn;
	
	public Book(){
		
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	
}
