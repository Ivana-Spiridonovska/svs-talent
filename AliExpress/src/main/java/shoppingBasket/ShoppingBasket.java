package shoppingBasket;

import java.util.ArrayList;

import product.Product;

public class ShoppingBasket {

	ArrayList<Product> basket = new ArrayList<Product>();

	public ShoppingBasket() {

	}

	public ArrayList<Product> getProductBasket() {
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
