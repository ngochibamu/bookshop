package za.absa.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import za.absa.bookstore.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
