public class BoxVolume {

	static int volume(int height, int weight, int depth) {

		return height * weight * depth;
	}

	public static void main(String[] args) {
		Box box1 = new Box();
		box1.height = 8;
		box1.weight = 5;
		box1.depth = 10;

		Box box2 = new Box();
		box2.height = 3;
		box2.weight = 15;
		box2.depth = 20;

		if (volume(box1.height, box1.weight, box1.depth) > volume(box2.height,box2.weight, box2.depth)) {
			System.out.println("Box1 has greater volume and it`s value is: "
					+ volume(box1.height, box1.weight, box1.depth));
		}

		else if (volume(box2.height, box2.weight, box2.depth) > volume(box1.height, box1.weight, box1.depth)) {
			System.out.println("Box2 has greater volume and it`s value is: "
					+ volume(box2.height, box2.weight, box2.depth));
		} else {
			System.out.println("Both boxes have same volume with value: "
					+ volume(box1.height, box1.weight, box1.depth));
		}

	}

}
