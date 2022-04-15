package za.absa.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Carts")
public class Cart extends BookstoreData {

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<LineItem> lineItems = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private CartStatus cartStatus;

    @OneToOne(mappedBy = "cart")
    private Order order;

    public Cart(Customer customer, Set<LineItem> lineItem, CartStatus cartStatus){
        this.customer = customer;
        this.lineItems = lineItem;
        this.cartStatus = cartStatus;
    }
}
