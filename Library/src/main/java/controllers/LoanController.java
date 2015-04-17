package controllers;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import dataAccess.hibernate.LoanDao;
import dataAccess.hibernate.MemberDao;
import dataAccess.hibernate.PublicationDao;
import pojoObjectsForHibernate.Loan;
import pojoObjectsForHibernate.Member;
import pojoObjectsForHibernate.Publication;
import app.Reader;

public class LoanController {

	private LoanDao loanDao = new LoanDao();
	private MemberDao memberDao = new MemberDao();
	private PublicationDao publicationDao = new PublicationDao();

	public void create() throws SQLException {
		Member member;
		Publication publication;
		Loan loan = new Loan();
		boolean chooseMember = false;
		boolean choosePublication = false;
		while (!chooseMember) {
			Reader.println("Enter id of the member:");
			int memberId = Integer.parseInt(Reader.readString());
			member = memberDao.getMemberById(memberId);
			if (member == null)
				Reader.println("Member with id " + memberId
						+ " doesn`t exist in database!");
			else {
				while (!choosePublication) {
					Reader.println("Enter id of the publication:");
					int publicationId = Integer.parseInt(Reader.readString());
					publication = publicationDao
							.getPublicationById(publicationId);
					
					if (publication == null) {
						Reader.println("Publication with id " + publicationId
								+ " doesn`t exist in database!");
					}else {
						loan.setMember(member);
						if (loanDao.publicationExistInLoan(publicationId)){
							Reader.println("That publication is already taken by another member!");
						}else{
							loan.setPublication(publication);
							Calendar calendar = Calendar.getInstance();
							loan.setStartDate(calendar.getTime());
							calendar.set(calendar.get(Calendar.YEAR),
									calendar.get(Calendar.MONTH) + 1,
									calendar.get(Calendar.DAY_OF_MONTH));
							loan.setEndDate(calendar.getTime());
							loanDao.createLoan(loan);
							choosePublication = true;
						}
					}
				}
				chooseMember = true;
			}
		}
	}

	public void list() {
		List<Loan> loans = loanDao.listLoans();
		System.out.printf("%s %10s %10s %n", "MemberID", "PublicationID",
				" StartDate ");
		for (Loan loan : loans) {
			Reader.println(loan.getMember().getId() + " \t "
					+ loan.getPublication().getId() + " \t "  + "\t " 
					+ loan.getStartDate());
		}
	}
}
