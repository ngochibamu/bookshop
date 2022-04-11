package za.absa.bookstore.model;

public enum OrderStatus {
    PENDING("Order placed, and waiting to be processed"),
    UNSHIPPED("Order is being processed"),
    SHIPPED("Order is on its way to customer's shipping address"),
    COMPLETED("Order received and received by customer"),
    CANCELED("Customer has cancelled order"),
    REFUND_APPLIED("Order item received and customer refunded");

    private final String description;

    private OrderStatus(String description) {
        this.description = description;
    }

    public String getOrderDescription(){
        return description;
    }
}
