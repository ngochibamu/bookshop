package za.absa.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import za.absa.bookstore.model.Cart;
import za.absa.bookstore.model.CartStatus;
import za.absa.bookstore.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Optional<Cart> findByCartStatus(CartStatus cartStatus);
    Optional<Cart> findByCustomerAndCartStatus(Customer customer, CartStatus cartStatus);
    List<Optional<Cart>> findAllByCustomerAndCartStatus(Customer customer, CartStatus cartStatus);
}
