package za.absa.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import za.absa.bookstore.dto.BookData;
import za.absa.bookstore.service.BookService;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/booklist", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookData> getBookList(){
        return bookService.getBooks();
    }
}
