package za.absa.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.absa.bookstore.dto.BookData;
import za.absa.bookstore.exception.BookstoreException;
import za.absa.bookstore.model.Book;
import za.absa.bookstore.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {


    private final BookRepository bookRepository;

    private final ServiceHelper serviceHelper;

    @Autowired
    public BookService(BookRepository bookRepository, ServiceHelper serviceHelper) {
        this.bookRepository = bookRepository;
        this.serviceHelper = serviceHelper;
    }

    public Book getBook(long bookId){
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookstoreException("Cannot find book with Id "+bookId));
    }

    public List<BookData> getBooks(){
        List<Book> bookList = StreamSupport
                .stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return serviceHelper.convertBooksToBookDataList(bookList);
    }
}
