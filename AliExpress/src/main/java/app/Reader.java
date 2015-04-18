package app;
import java.util.Scanner;

public class Reader {
	private static final Scanner input = new Scanner(System.in);

	public static void println(String s){
		System.out.println(s); 
	}
	
	public static String readString(){
		return input.next();
	}
}
