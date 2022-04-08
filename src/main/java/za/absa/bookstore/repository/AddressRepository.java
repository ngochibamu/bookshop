package za.absa.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import za.absa.bookstore.model.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
