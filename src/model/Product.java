package model;

public class Product {
	private int id;
	private String name;
	private Amount publicPrice = new Amount(0);
	private Amount wholesalerPrice = new Amount(0);
	private boolean available;
	private int stock;
	private static int totalProducts;

	static Amount EXPIRATION_RATE = new Amount(0.6);

	public Product(String name, Amount wholesalerPrice, boolean available, int stock) {
		super();
		this.id = totalProducts + 1;
		this.name = name;
		this.wholesalerPrice = wholesalerPrice;
                publicPrice = new Amount (1 + (wholesalerPrice.getValue() * 13.0 / 10.0));
		this.available = available;
		this.stock = stock;
		totalProducts++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Amount getPublicPrice() {
		return publicPrice;
	}

	public void setPublicPrice(Amount publicPrice) {
		this.publicPrice = publicPrice;
	}

	public Amount getWholesalerPrice() {
		return wholesalerPrice;
	}

	public void setWholesalerPrice(Amount wholesalerPrice) {
		this.wholesalerPrice = wholesalerPrice;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public static int getTotalProducts() {
		return totalProducts;
	}

	public static void setTotalProducts(int totalProducts) {
		Product.totalProducts = totalProducts;
	}
/**
 * Change the expiration rate of a product and set a new value to it
 */
	public void expire() {
		EXPIRATION_RATE.setValue(0.2);
                publicPrice.setValue(publicPrice.getValue()*EXPIRATION_RATE.getValue());
	}

   // @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", publicPrice=" + publicPrice + ", wholesalerPrice=" + wholesalerPrice + ", available=" + available + ", stock=" + stock + '}';
    }
        
        
}