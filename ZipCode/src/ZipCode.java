public class ZipCode {
	private String zipCode;

	public void setZipCode(String code) {
		if ((code.length() == 5) || (code.length() == 9))
			zipCode = code;
		else
			zipCode = "";
	}

	public String getZipCode() {
		return zipCode;
	}

	public static void main(String[] arguments) {
		ZipCode zip1 = new ZipCode();
		ZipCode zip2 = new ZipCode();
		zip1.setZipCode("51484");
		zip2.setZipCode("12345678");
		if (zip1.getZipCode() != "")
			System.out.println("Zip1: " + zip1.getZipCode());
		if (zip2.getZipCode() != "")
			System.out.println("Zip2: " + zip2.getZipCode());
	}
}
