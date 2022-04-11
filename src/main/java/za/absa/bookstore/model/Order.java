package za.absa.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Orders")
public class Order extends BookstoreData {

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order(Cart cart, BigDecimal totalPrice, LocalDateTime orderDate, OrderStatus orderStatus){
        this.cart = cart;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }
}
