package pojoObjectsForHibernate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "magazine")
@DiscriminatorValue("magazine")
public class Magazine extends Publication{

	@Column(unique = true, name= "issn") private String issn;
	
	public Magazine(){
		
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	
}
