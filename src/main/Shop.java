package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import model.Product;
import model.Sale;
import java.util.Scanner;
import model.Amount;
import model.Client;
import model.Employee;
import model.Person;

public class Shop {

    private Amount cash = new Amount(100);

    private ArrayList<Product> inventory = new ArrayList<>();
    private int numberProducts;
    private ArrayList<Sale> sales = new ArrayList<>();

    final static Amount TAX_RATE = new Amount(1.04);

    public Shop() {
    }

    public static void main(String[] args) {
        System.out.println("Hola mundo");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca tu nombre, empleado");
        String nombre = scanner.nextLine();
        Employee empleado = new Employee(nombre);

        int num = 0;
        boolean check_employee_id = false;
        String contrasenya = null;
        do {
            scanner = new Scanner(System.in);
            try {
                System.out.println("Introduzca numero de empleado");
                num = scanner.nextInt();
                scanner = new Scanner(System.in);
                System.out.println("Introduzca contraseÒa");
                contrasenya = scanner.nextLine();
                check_employee_id = true;
            } catch (InputMismatchException error) {
                System.err.println("Introduce valores v·lidos por favor");
            }
        } while (check_employee_id == false);
        if (empleado.login(num, contrasenya)) {
            Shop shop = new Shop();

            shop.loadInventory();

            int opcion = 0;
            boolean exit = false;

            do {
                System.out.println("\n");
                System.out.println("===========================");
                System.out.println("Menu principal miTienda.com");
                System.out.println("===========================");
                System.out.println("1) Contar caja");
                System.out.println("2) A√±adir producto");
                System.out.println("3) A√±adir stock");
                System.out.println("4) Marcar producto proxima caducidad");
                System.out.println("5) Ver inventario");
                System.out.println("6) Venta");
                System.out.println("7) Ver ventas");
                System.out.println("8) Ver venta total");
                System.out.println("9) Eliminar producto");
                System.out.println("10) Salir programa");
                System.out.print("Seleccione una opci√≥n: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        shop.showCash();
                        break;

                    case 2:
                        shop.addProduct();
                        break;

                    case 3:
                        shop.addStock();
                        break;

                    case 4:
                        shop.setExpired();
                        break;

                    case 5:
                        shop.showInventory();
                        break;

                    case 6:
                        shop.sale();
                        break;

                    case 7:
                        shop.showSales();
                        break;
                    case 8:
                        shop.totalAmountSale();
                        break;
                    case 9:
                        shop.deleteProduct();
                        break;

                    case 10:
                        exit = true;
                        break;
                }
            } while (!exit);
        } else {
            System.out.println("El login no se hizo con Èxito");
        }

    }

    /**
     * load initial inventory to shop
     */
    public void loadInventory() {
        inventory.add(new Product("Pera", new Amount(20.00), true, 20));
        inventory.add(new Product("Manzana", new Amount(10.00), true, 10));
        inventory.add(new Product("Hamburguesa", new Amount(30.00), true, 30));
        inventory.add(new Product("Fresa", new Amount(5.00), true, 20));
    }

    /**
     * show current total cash
     */
    private void showCash() {
        System.out.println("Dinero actual: " + cash);
    }

    /**
     * add a new product to inventory getting data from console
     */
    public void addProduct() {
        boolean check = false;
        do {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("Nombre: ");
                String name = scanner.nextLine();
                if (findProduct(name) == null) {
                    System.out.print("Precio mayorista: ");
                    Amount wholesalerPrice = new Amount(0);
                    wholesalerPrice.setValue(scanner.nextDouble());
                    System.out.print("Stock: ");
                    int stock = scanner.nextInt();
                    addProduct(new Product(name, wholesalerPrice, true, stock));
                    check = true;
                } else {
                    System.out.println("No se puede agregar, producto repetido");
                }
            } catch (InputMismatchException e) {
                System.err.println("Inserte valor v·lido por favor");
                System.err.println("Empecemos de cero");
            }

        } while (check == false);

    }

    /**
     * add stock for a specific product
     */
    public void addStock() {
        boolean check = false;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione un nombre de producto: ");
        String name = scanner.next();
        Product product = findProduct(name);
        if (product != null) {

            do {
                scanner = new Scanner(System.in);
                try {
                    // ask for stock
                    System.out.print("Seleccione la cantidad a a√±adir: ");
                    int stock = scanner.nextInt();
                    // update stock product
                    product.setStock(stock + product.getStock());
                    System.out.println("El stock del producto " + name + " ha sido actualizado a " + product.getStock());
                    check = true;
                } catch (InputMismatchException e) {
                    System.err.println("Inserte valor v·lido por favor");
                    System.err.println("Empecemos de cero");
                }

            } while (check == false);

        } else {
            System.out.println("No se ha encontrado el producto con nombre " + name);
        }
    }

    /**
     * set a product as expired
     */
    private void setExpired() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione un nombre de producto: ");
        String name = scanner.next();

        Product product = findProduct(name);

        if (product != null) {
            product.expire();
            System.out.println("El stock del producto " + name + " ha sido actualizado a " + product.getPublicPrice());

        }
    }

    /**
     * show all inventory
     */
    public void showInventory() {
        System.out.println("Contenido actual de la tienda:");
        for (Product product : inventory) {
            if (product != null) {
                System.out.println(product);
            }
        }
    }

    /**
     * make a sale of products to a client
     */
    public void sale() {
        // ask for client name
        boolean check_num_client = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Realizar venta, escribir nombre cliente");
        String nom_client = sc.nextLine();
        int num_client = -1;
        do {
            sc = new Scanner(System.in);
            try {
                System.out.println("Escribir numero de socio");
                num_client = sc.nextInt();
                check_num_client = true;
            } catch (InputMismatchException e) {
                System.err.println("Inserte un numero de socio valido");
            }

        } while (check_num_client == false);

        Client cliente = new Client(num_client, nom_client);
        // sale product until input name is not 0
        Amount totalAmount = new Amount(0.0);
        Amount total_amount_sales = new Amount(0.0);
        String name = "";
        //Product array_temporal[] = new Product[5];
        ArrayList<Product> array_temporal = new ArrayList<>();

        while (!name.equals("0")) {
            sc = new Scanner(System.in);
            System.out.println("Introduce el nombre del producto, escribir 0 para terminar:");
            name = sc.nextLine();
            if (name.equals("0")) {
                break;
            }
            Product product = findProduct(name);
            boolean productAvailable = false;

            if (product != null && product.isAvailable()) {
                productAvailable = true;
                totalAmount.setValue(totalAmount.getValue() + product.getPublicPrice().getValue());
                product.setStock(product.getStock() - 1);
                //array_temporal[contador_array]= product;
                array_temporal.add(product);

                // if no more stock, set as not available to sale
                if (product.getStock() == 0) {
                    product.setAvailable(false);
                }
                System.out.println("Producto a√±adido con √©xito");

            }

            if (!productAvailable) {
                System.out.println("Producto no encontrado o sin stock");
            }
        }

        // show cost total
        
        total_amount_sales.setValue(total_amount_sales.getValue() + totalAmount.getValue());
        //totalAmount = totalAmount * TAX_RATE;
        totalAmount.setValue(totalAmount.getValue() * TAX_RATE.getValue());
        //cash += totalAmount;
        System.out.println("Venta realizada con √©xito, total: " + totalAmount);
        System.out.println(Product.getTotalProducts());
        //Sale venta = new Sale(client, array_temporal, num, totalAmount);
        Sale venta = new Sale(cliente, totalAmount);
        System.out.println("El cliente " + venta.getCliente().getNombre() + "tenÌa " + venta.getCliente().getSaldo());
        // asign nuevo_saldo to the new cash of the amount of the client when he purchase 
        Amount nuevo_saldo = new Amount(venta.getCliente().getSaldo().getValue() - totalAmount.getValue());
        venta.getCliente().setSaldo(nuevo_saldo);
        if (nuevo_saldo.getValue() > 0) {
            System.out.println("El cliente " + venta.getCliente() + " ahora tiene " + venta.getCliente().getSaldo());
            cash.setValue(cash.getValue() + totalAmount.getValue());
        } else {
            System.out.println("El cliente " + venta.getCliente() + " debe " + venta.getCliente().getSaldo());
            cash.setValue(cash.getValue() + totalAmount.getValue() - venta.getCliente().getSaldo().getValue());
        }
        sales.add(venta);
        Sale.getTotal_amount().setValue(Sale.getTotal_amount().getValue() + venta.getAmount().getValue());

    }

    /**
     * show all sales
     */
    private void showSales() {
        if (Sale.getSales_quantity() != 0) {
            System.out.println("Lista de ventas:");
            for (Sale sale : sales) {
                if (sale != null) {
                    System.out.println(sale);
                    System.out.println("");
                }
            }
        } else {
            System.out.println("No hay ventas registradas");
        }
    }

    /**
     * add a product to inventory
     *
     * @param product
     */
    public void addProduct(Product product) {

        inventory.add(product);
    }

    /**
     * check if inventory is full or not
     *
     * @return true if inventory is full
     */
    /**
     * find product by name
     *
     * @param name
     * @return product found by name
     */
    public Product findProduct(String name) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i) != null && inventory.get(i).getName().equals(name)) {
                return inventory.get(i);
            }
        }
        return null;
    }

    /**
     * delete a product by his name if he find it
     */
    public void deleteProduct() {
        Scanner escaner = new Scanner(System.in);
        System.out.println("Dame el nombre del producto a eliminar");
        String nombre = escaner.nextLine();
        Product producto_temporal = findProduct(nombre);
        if (producto_temporal != null) {
            String eliminado = producto_temporal.getName();
            inventory.remove(producto_temporal);
            System.out.println("El artÌculo " + eliminado + " ha sido removido");
        } else {
            System.out.println("El producto no se ha encontrado");
        }

    }

    /**
     * Show total amount of sale
     */
    public static void totalAmountSale() {
        System.out.println("La venta total es de " + Sale.getTotal_amount());
    }
}
