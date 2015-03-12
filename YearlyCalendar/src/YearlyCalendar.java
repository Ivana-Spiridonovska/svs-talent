public class YearlyCalendar {

	static int countDays(int month, int year) {
		int count = -1;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			count = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			count = 30;
			break;
		case 2:
			if (year % 4 == 0) {
				count = 29;
			} else {
				count = 28;
			}
			if ((year % 100 == 0) & (year % 400 != 0)) {
				count = 28;
			}
		}
		return count;
	}

	public static void main(String[] args) {

		int year = 2016;
		int days = 0;
		for (int i = 1; i <= 12; i++) {
			days += countDays(i, year);
		}
		String[] list = new String[days];

		// System.out.println("Year " + year + " has " + days + " days ");

		int i = 0;
		for (int month = 1; month <= 12; month++) {
			for (int day = 1; day <= countDays(month, year); day++) {
				list[i++] = day + "/" + month + "/" + year;
			}
		}

		for (int j = 0; j < list.length; j++) {
			System.out.println(list[j]);
		}
	}
}
