package shoppingBasket;

import java.util.Vector;

import product.Product;

public class ShoppingBasket {

	Vector<Product> basket = new Vector<Product>();

	public ShoppingBasket() {

	}

	public Vector<Product> getProductBasket() {
		return this.basket;
	}

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

	public int totalSum() {
		int total = 0;

		for (Product product : basket) {
			total += product.getPrice() * product.getQuantity();
		}
		return total;
	}

}
