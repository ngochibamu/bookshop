package za.absa.bookstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Customers")
public class Customer extends AbstractEntity{

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();

    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY, orphanRemoval = true)
    private Cart cart;
}
