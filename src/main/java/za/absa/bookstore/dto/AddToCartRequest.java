package za.absa.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddToCartRequest {
    private long bookId;
    private int quantity;
    private long customerId;
}
