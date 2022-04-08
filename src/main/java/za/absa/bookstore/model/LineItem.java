package za.absa.bookstore.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "Line_items")
public class LineItem extends BookstoreData {

    private int quantity;

    private BigDecimal price;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    public LineItem(int quantity, BigDecimal price, Book book){
        this.quantity = quantity;
        this.price = price;
        this.book = book;
    }
}
