import java.util.Vector;

public class ShoppingBasket implements ShoppingBasketInterface {

	Vector<Product> basket;

	public ShoppingBasket() {
		basket = new Vector<Product>();
	}

	@Override
	public void addProduct(Product product) {
		boolean changed = false;

		for (Product item : basket) {
			if (item.getUniqueKey().equals(product.getUniqueKey())) {
				item.setQuantity(item.getQuantity() + product.getQuantity());
				changed = true;
				break;
			}
		}

		if (!changed) {
			basket.add(product);
		}
	}
	
	/*public Vector<Product> getProductBasket(){
		return this.basket;
	}*/

	@Override
	public int totalSum() {
		int total = 0;

		for (Product product : basket) {
			total += product.getPrice() * product.getQuantity();
		}
		return total;
	}

	@Override
	public void printBasket() {
		if (basket.isEmpty()) {
			System.out.println("Your basket is empty! \n");
		} else {
			System.out.printf("%s %15s %15s %n", " Name ", " Price",
					" Quantity");

			for (Product product : basket) {
				System.out.println(product.getName() + " \t "
						+ product.getPrice() + " \t " + " \t "
						+ product.getQuantity());
			}
			System.out.println("\n" + "Total price is: " + totalSum() + "\n");
		}

	}

}
