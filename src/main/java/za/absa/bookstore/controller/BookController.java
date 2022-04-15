package za.absa.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import za.absa.bookstore.dto.BookData;
import za.absa.bookstore.service.BookService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/booklist", produces = APPLICATION_JSON_VALUE)
    public List<BookData> getBookList(){
        return bookService.getBooks();
    }
}
