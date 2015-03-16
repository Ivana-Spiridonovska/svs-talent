public class Numbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int number = 0;
		int result = 0;
		char firstChar;
		char secondChar;
		int length = args.length;

		if (length > 0) {
			for (int i = 0; i < length; i++) {
				firstChar = args[i].charAt(0);
				secondChar = args[i].charAt(1);

				switch (firstChar) {
				case 'z':
					number = 0;
					break;
				case 'o':
					number = 1;
					break;
				case 't':
					if (secondChar == 'w')
						number = 2;
					if (secondChar == 'h')
						number = 3;
					break;
				case 'f':
					if (secondChar == 'o')
						number = 4;
					if (secondChar == 'i')
						number = 5;
					break;
				case 's':
					if (secondChar == 'i')
						number = 6;
					if (secondChar == 'e')
						number = 7;
					break;
				case 'e':
					number = 8;
					break;
				case 'n':
					number = 9;
					break;

				}
				result += number * Math.pow(10, length - 1 - i);

			}
			System.out.println(result);
		} else
			System.out.println("You have to enter word/s from zero to nine");

	}

}
