package za.absa.bookstore.dto;

import lombok.Builder;
import lombok.Getter;
import za.absa.bookstore.model.CartStatus;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Builder
public class CartData {

    private CustomerData customerData;
    private Set<LineItemData> lineItems;
    private CartStatus cartStatus;
    private BigDecimal totalPrice;
}
