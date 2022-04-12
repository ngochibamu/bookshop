package za.absa.bookstore.service;

import org.springframework.stereotype.Component;
import za.absa.bookstore.dto.BookData;
import za.absa.bookstore.dto.LineItemData;
import za.absa.bookstore.model.Book;
import za.absa.bookstore.model.Cart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ServiceHelper {

    public BookData convertBookToBookData(Book book){
        return BookData.builder()
                .author(book.getAuthor())
                .description(book.getDescription())
                .title(book.getTitle())
                .ISBN(book.getISBN())
                .numberOfPages(book.getNumberOfPages())
                .build();
    }

    public List<BookData> convertBooksToBookDataList(List<Book> books){

        List<BookData> bookDataList = new ArrayList<>();
        books.forEach( (bk) -> {
            bookDataList.add(convertBookToBookData(bk));
        });
        return bookDataList;
    }


    public Set<LineItemData> convertToItemData(Cart cart){
        Set<LineItemData> itemDataSet = new HashSet<>();
        cart.getLineItems().forEach(
                (lineItem) -> {
                    LineItemData itemData =  LineItemData.builder()
                            .price(lineItem.getPrice())
                            .quantity(lineItem.getQuantity())
                            .bookData(convertBookToBookData(lineItem.getBook()))
                            .build();
                    itemDataSet.add(itemData);
                }
        );
        return itemDataSet;
    }

}
