package za.absa.bookstore.service.api;

import za.absa.bookstore.dto.OrderData;
import za.absa.bookstore.model.OrderStatus;

public interface OrderService {
    void placeOrder(long customerId);
    OrderData getOrderForCustomer(long customerId, OrderStatus orderStatus);
}
