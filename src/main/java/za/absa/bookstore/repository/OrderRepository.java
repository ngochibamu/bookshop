package za.absa.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.absa.bookstore.model.Cart;
import za.absa.bookstore.model.Customer;
import za.absa.bookstore.model.Order;
import za.absa.bookstore.model.OrderStatus;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
