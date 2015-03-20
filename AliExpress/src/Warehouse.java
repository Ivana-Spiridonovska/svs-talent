import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Warehouse implements WarehouseInterface {

	private Vector<Product> products;

	public Warehouse() {
		storeData();
	}

	public Vector<Product> getProducts() {
		return this.products;
	}

	@Override
	public boolean containsProductWithKey(String key) {
		boolean isInWarehouse = false;

		for (Product product : products) {
			if (product.getUniqueKey().equals(key)) {
				isInWarehouse = true;
				break;
			}
		}
		return isInWarehouse;
	}

	public Product update(String productKey, int quantityRequestedByBuyer)
			throws QuantityException {

		Product soldProduct = null;

		for (Product product : products) {
			if (product.getUniqueKey().equals(productKey)) {

				if (product.getQuantity() - quantityRequestedByBuyer < 0) {
					throw new QuantityException(
							"Not enough products in the store!");
				} else {
					product.setQuantity(product.getQuantity()
							- quantityRequestedByBuyer);
				}
				soldProduct = new Product(product.getUniqueKey(),
						product.getName(), product.getPrice(),
						quantityRequestedByBuyer);
			}
		}
		return soldProduct;
	}

	public void storeData() {
		products = new Vector<Product>();
		BufferedReader buff = null;

		try {
			FileReader file = new FileReader("smartPhones.txt");
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
