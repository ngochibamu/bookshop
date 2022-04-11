package za.absa.bookstore.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class LineItemData {
    private int quantity;
    private BigDecimal price;
    private BookData bookData;
}
