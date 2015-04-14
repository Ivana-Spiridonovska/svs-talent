package warehouse;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import pojoObjects.Product;

public class WarehouseInMemory implements WarehouseInterface {

	private  ArrayList<Product> products;

	public WarehouseInMemory() {

	}

	public ArrayList<Product> getProducts() {
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
	public void update(Product boughtProduct) {
		Product productWithKey = getProductWithKey(boughtProduct.getUniqueKey());
		productWithKey.setQuantity(boughtProduct.getQuantity());
	}

	@Override
	public void storeData() {
		products = new  ArrayList<Product>();
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
