package za.absa.bookstore.dto;

import lombok.Builder;
import lombok.Getter;
import za.absa.bookstore.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
