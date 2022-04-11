package za.absa.bookstore.dto;

import lombok.*;
import za.absa.bookstore.model.CartStatus;
import za.absa.bookstore.model.LineItem;
import za.absa.bookstore.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
public class OrderData {

    private Long orderId;
    private CustomerData customerData;
    private BigDecimal totalPrice;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Set<LineItemData> lineItems;

}
