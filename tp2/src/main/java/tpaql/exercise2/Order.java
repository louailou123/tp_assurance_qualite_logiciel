package tpaql.exercise2;

public class Order {
    private long id;
    private String productName;
    private int quantity;

    public Order(long id, String productName, int quantity) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }
}