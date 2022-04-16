package za.absa.bookstore.service;

import org.springframework.stereotype.Component;
import za.absa.bookstore.dto.AddressData;
import za.absa.bookstore.dto.BookData;
import za.absa.bookstore.dto.CustomerData;
import za.absa.bookstore.dto.LineItemData;
import za.absa.bookstore.model.Address;
import za.absa.bookstore.model.Book;
import za.absa.bookstore.model.Cart;
import za.absa.bookstore.model.Customer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ServiceHelper {

    public BookData BookToBookData(Book book){
        return BookData.builder()
                .bookId(book.getId())
                .author(book.getAuthor())
                .description(book.getDescription())
                .title(book.getTitle())
                .ISBN(book.getISBN())
                .numberOfPages(book.getNumberOfPages())
                .language(book.getLanguage())
                .price(book.getPrice())
                .weight(book.getWeight())
                .dimensions(book.getDimensions())
                .publisher(book.getPublisher())
                .imageUrl(book.getImageUrl())
                .build();
    }

    public List<BookData> convertBooksToBookDataList(List<Book> books){

        List<BookData> bookDataList = new ArrayList<>();
        books.forEach( (bk) -> {
            bookDataList.add(BookToBookData(bk));
        });
        return bookDataList;
    }


    public Set<LineItemData> LineItemToLineItemData(Cart cart){
        Set<LineItemData> itemDataSet = new HashSet<>();
        cart.getLineItems().forEach(
                (lineItem) -> {
                    LineItemData itemData =  LineItemData.builder()
                            .price(lineItem.getPrice())
                            .quantity(lineItem.getQuantity())
                            .bookData(BookToBookData(lineItem.getBook()))
                            .build();
                    itemDataSet.add(itemData);
                }
        );
        return itemDataSet;
    }

    public CustomerData customerToCustomerData(Customer customer){
        return CustomerData.builder()
                .emailAddress(customer.getEmailAddress())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .address(addressToAddressData(customer.getAddress()))
                .build();
    }


    public Set<AddressData> addressToAddressData(Set<Address> addresses){
        Set<AddressData> addressesData = new HashSet<>();
        addresses.forEach( (address) -> {
            AddressData addressData = AddressData.builder()
                    .addressLine1(address.getAddressLine1())
                    .addressLine2(address.getAddressLine2())
                    .city(address.getCity())
                    .country(address.getCountry())
                    .postalCode(address.getPostalCode())
                    .state(address.getState())
                    .type(address.getType())
                    .build();
            addressesData.add(addressData);
        });
        return addressesData;
    }
}
