package za.absa.bookstore.service.api;

import za.absa.bookstore.dto.OrderData;
import za.absa.bookstore.model.OrderStatus;

import java.util.List;

public interface OrderService {
    void placeOrder(long customerId);
    OrderData getNewOrderForCustomer(long customerId, OrderStatus orderStatus);
    List<OrderData> getOrdersForCustomer(long customerId);
}
