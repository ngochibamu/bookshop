package za.absa.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.absa.bookstore.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
