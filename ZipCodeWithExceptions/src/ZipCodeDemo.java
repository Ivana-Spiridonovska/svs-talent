public class ZipCodeDemo {
	public static void main(String[] args) {

		ZipCodeWithExceptions zip1 = new ZipCodeWithExceptions();
		ZipCodeWithExceptions zip2 = new ZipCodeWithExceptions();
		try {
			zip1.setZipCode("12345");
			System.out.println(zip1.getZipCode());
			zip2.setZipCode("12345678");

		} catch (ZipCodeException ex) {
			System.out.println(zip2.getZipCode() + " is not a valid zip code");
			// ex.printStackTrace();
		}

	}

}
