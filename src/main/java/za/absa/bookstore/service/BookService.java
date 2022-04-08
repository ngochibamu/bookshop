package za.absa.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.absa.bookstore.exception.BookstoreException;
import za.absa.bookstore.model.Book;
import za.absa.bookstore.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book getBook(long bookId){
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookstoreException("Cannot find book with Id "+bookId));
    }
}
