import java.util.Calendar;
import java.util.Date;

public class TimePeriod {
	private Date startDate;
	private Date endDate;

	public TimePeriod() {

	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	


	public TimePeriod(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public boolean overlapsWith(TimePeriod periodB) {
		boolean overlaps = false;
		TimePeriod periodA = new TimePeriod(startDate,endDate);
		if (periodA.getStartDate().before(periodA.getEndDate()) && periodB.getStartDate().before(periodB.getEndDate())){
			
			if (periodA.getEndDate().before(periodB.getStartDate()))
				overlaps = false;
			
			if (periodA.getStartDate().after(periodB.getEndDate()))
				overlaps = false;
			
			if (periodA.getStartDate().equals(periodB.getStartDate())
					&& periodA.getEndDate().equals(periodB.getEndDate()))
				overlaps = true;	
			
			if (periodB.getStartDate().before(periodA.getStartDate())
					&& periodB.getEndDate().after(periodA.getStartDate()))
				overlaps = true;
			
			if (periodB.getStartDate().after(periodA.getStartDate())
					&& periodB.getStartDate().before(periodA.getEndDate()))
				overlaps = true;	
		}	
		return overlaps;
	}

	public static Date setDate(int day, int month, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		Date date = calendar.getTime();
		return date;
	}

}
