package controllers;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import app.Reader;
import dataAccess.hibernate.MemberDao;
import pojoObjectsForHibernate.Member;
import pojoObjectsForHibernate.Membership;

public class MemberController {

	private Member member = new Member();
	private Membership membership = new Membership();
	private MemberDao memberDatabase = new MemberDao();
	private EmailValidator validator = new EmailValidator();

	public void register() throws SQLException {
		Reader.println("Enter name:");
		String name = Reader.readString();
		member.setName(name);
		
		boolean done = false;
		while(!done){
			Reader.println("Enter email:");
			String email = Reader.readString();
			try{
				if (validator.validate(email)) {
					member.setEmail(email);
					done = true;
				} else {
					done = false;
					throw new InvalidEmailException("That is not a valid email!");
				}
			}catch (InvalidEmailException e){
				e.printMessage();
			}	
		}
		
		Reader.println("Enter type of membership(6 months or 12 months):");
		String membershipType = Reader.readString();
		membership.setMembershipType(membershipType);
		Calendar calendar = Calendar.getInstance();
		Date startDate = calendar.getTime();
		membership.setStartDate(startDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (month + Integer.parseInt(membershipType) > 12)
		  calendar.set(year + 1, (month + Integer.parseInt(membershipType))-12, day);
		else
		  calendar.set(year, month + Integer.parseInt(membershipType), day);
		Date endDate = calendar.getTime();
		membership.setEndDate(endDate);
		membership.setMember(member);
		memberDatabase.register(membership);
	}

	public void unregister() throws SQLException {
		Member member;
		boolean done = false;
		while (!done) {
			Reader.println("Enter id of the member you want to unregister:");
			try{
				int id = Integer.parseInt(Reader.readString());
				member = memberDatabase.getMemberById(id);
				if (member == null)
					Reader.println("Member with id " + id + " doesn`t exist in database!");
				else{
					membership.setMember(member);
					memberDatabase.unregister(membership);
					Reader.println("You have successfully unregister member with id "
							+ id + "!");
					done = true;
				}	
			}catch(NumberFormatException e){
				Reader.println("You have to enter number!");
				done = false;
			}
		}
	}
	
	public void findMembers() {
		List<Member> members = memberDatabase.listMembers();
		System.out.printf("%s %10s %10s %n", "ID", "Name", " Email ");
		for (Member member : members) {
			Reader.println(member.getId() + " \t " + member.getName()
					+ " \t " + member.getEmail());
		}
	}
}
