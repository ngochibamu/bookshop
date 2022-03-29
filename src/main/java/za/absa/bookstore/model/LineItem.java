package za.absa.bookstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "OrderLines")
public class LineItem extends AbstractEntity {

    private int quantity;

    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Order order;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_id", referencedColumnName = "ID")
    private Book book;
}
