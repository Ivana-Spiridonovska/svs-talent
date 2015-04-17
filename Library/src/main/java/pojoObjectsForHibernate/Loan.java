package pojoObjectsForHibernate;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "loan")
public class Loan {
	
	@Id
	@GeneratedValue private int id;
	@Column(name ="endDate") private Date endDate;
	@Column(name ="startDate") private Date startDate;
	
    @ManyToOne(cascade= {CascadeType.ALL})
    private Member member;
    
    @ManyToOne(cascade= {CascadeType.ALL})
    private Publication publication;

	public Loan(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	
	
	
}
