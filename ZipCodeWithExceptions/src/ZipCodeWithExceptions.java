public class ZipCodeWithExceptions {
	String zipCode;

	public void setZipCode(String zipCode) throws ZipCodeException {
		if (zipCode.length() == 5 || zipCode.length() == 9) {
			this.zipCode = zipCode;
		} else
			throw new ZipCodeException();
	}

	public String getZipCode() {
		return zipCode;
	}

}
