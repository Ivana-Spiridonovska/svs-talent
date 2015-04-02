package warehouse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import product.Product;

public class WarehouseInMemory implements WarehouseInterface {

	private Vector<Product> products;

	public WarehouseInMemory() {

	}

	public Vector<Product> getProducts() {
		return this.products;
	}

	@Override
	public Product getProductWithKey(String key) {
		Product productWithKey = null;

		for (Product product : products) {
			if (product.getUniqueKey().equals(key)) {
				productWithKey = product;
				break;
			}
		}
		return productWithKey;

	}

	@Override
	public Product getBoughtProduct(String productKey, int quantityRequestedByBuyer)
			throws QuantityException {

		Product soldProduct = null;
		Product productWithKey = getProductWithKey(productKey);

		if (productWithKey.getQuantity() - quantityRequestedByBuyer < 0) {
			throw new QuantityException("Not enough products in the store!");
		} else {
			soldProduct = new Product(productWithKey.getUniqueKey(),
					productWithKey.getName(), productWithKey.getPrice(),
					quantityRequestedByBuyer);
		}

		return soldProduct;
	}

	@Override
	public void update(Product boughtProduct) {
		Product productWithKey = getProductWithKey(boughtProduct.getUniqueKey());
		productWithKey.setQuantity(productWithKey.getQuantity()
				- boughtProduct.getQuantity());

	}

	@Override
	public void storeData() {
		products = new Vector<Product>();
		BufferedReader buff = null;

		try {
			FileReader file = new FileReader(
					"./src/main/resources/smartPhones.txt");
			buff = new BufferedReader(file);

			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				if (line == null) {
					eof = true;
				} else {
					String[] splitLine = line.split("\\|");
					products.add(new Product(splitLine[0], splitLine[1],
							Integer.parseInt(splitLine[2]), Integer
									.parseInt(splitLine[3])));
				}
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				buff.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
