package dictionary;

public class Product {
	String productName;
	double productPrice;
	int productQuantity;
	String productID;
	
	public Product(String productID, String productName, double productPrice, int productQuantity) {
		this.productID = productID;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
	}
}
