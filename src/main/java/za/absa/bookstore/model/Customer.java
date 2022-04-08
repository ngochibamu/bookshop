package za.absa.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Customers")
public class Customer extends BookstoreData {

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private Set<Order> orders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> address;

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private List<Cart> cart;
}
