package za.absa.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.absa.bookstore.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
