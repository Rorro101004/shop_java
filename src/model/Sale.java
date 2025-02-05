package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Sale {

        Client cliente ;
        ArrayList<Product> products = new ArrayList<>();
	Amount amount;
        static int sales_quantity = 0;
        static Amount total_amount = new Amount(0);

    public Sale(Client cliente, Amount amount) {
        this.cliente = cliente;
        this.amount = amount;
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

    public static int getSales_quantity() {
        return sales_quantity;
    } 

    public static Amount getTotal_amount() {
        return total_amount;
    }

    public static void setTotal_amount(Amount total_amount) {
        Sale.total_amount = total_amount;
    }        

    @Override
    public String toString() {
        return "Sale{" + "cliente=" + cliente + ", products=" + products + ", amount=" + amount + '}';
    }
    

}