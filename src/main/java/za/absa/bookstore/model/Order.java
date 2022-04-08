package za.absa.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "Orders")
public class Order extends BookstoreData {

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private Set<Cart> carts;

    public Order(BigDecimal totalPrice, LocalDateTime orderDate, Customer customer){
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.customer = customer;
    }
}
