package za.absa.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import za.absa.bookstore.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
