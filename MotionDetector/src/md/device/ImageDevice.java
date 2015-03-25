package md.device;

import java.util.Scanner;

public class ImageDevice implements ImageCapturingDevice {

	@Override
	public byte[] getImage() {
		String image =null;
		System.out.println("Enter string (it will be converted in array of bytes-image):");
		Scanner userInput = new Scanner(System.in);
	    image = userInput.next();
		return image.getBytes();
	}

}
