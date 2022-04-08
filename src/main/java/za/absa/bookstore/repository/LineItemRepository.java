package za.absa.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import za.absa.bookstore.model.LineItem;

public interface LineItemRepository extends CrudRepository<LineItem, Long> {
}
