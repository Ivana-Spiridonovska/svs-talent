import java.util.StringTokenizer;

public class DateParser {

	public static void main(String[] args) {

		String birthday = "03/26/1992";
		System.out.println("Birthday : " + birthday);
		StringTokenizer str = new StringTokenizer(birthday, "/");
		System.out.println("Month : " + str.nextToken());
		System.out.println("Day : " + str.nextToken());
		System.out.println("Year : " + str.nextToken());

	}

}
