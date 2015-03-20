import java.util.Vector;

public class WarehousePrinter {
	static Warehouse warehouse = new Warehouse();
	static Vector<Product> products= warehouse.getProducts();
 
	public static void print() {
 		System.out.printf("%s %10s %15s %n", "Key", " Name ", " Price");
 		for (Product product : products) {
 			System.out.println(product.getUniqueKey() + " \t "
 					+ product.getName() + " \t " + product.getPrice());
 		}
 	}
}
