package za.absa.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.absa.bookstore.exception.BookstoreException;
import za.absa.bookstore.model.Cart;
import za.absa.bookstore.model.CartStatus;
import za.absa.bookstore.model.Customer;
import za.absa.bookstore.model.LineItem;
import za.absa.bookstore.repository.BookRepository;
import za.absa.bookstore.repository.CartRepository;
import za.absa.bookstore.service.api.CartService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;

@Service
public class BookCartService implements CartService {

    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final CustomerService customerService;

    @Autowired
    public BookCartService(
            CartRepository cartRepository,
            BookRepository bookRepository,
            CustomerService customerService) {
        this.cartRepository = cartRepository;
        this.bookRepository = bookRepository;
        this.customerService = customerService;
    }

    @Override
    public void addToCart(long bookId, Integer quantity, long customerId) {

        bookRepository.findById(bookId).ifPresentOrElse(
                (book) -> {
                    LineItem lineItem = new LineItem(quantity,
                            book.getPrice().multiply(BigDecimal.valueOf(quantity)), book);
                    cartRepository
                            .findByCustomerAndCartStatus(customerService.getCustomer(customerId), CartStatus.OPEN)
                            .ifPresentOrElse(
                            (cart) -> {
                                cart.getLineItems().add(lineItem);
                                lineItem.setCart(cart);
                                cartRepository.save(cart);
                                },
                            () -> {
                                Cart cart = new Cart(customerService.getCustomer(customerId),
                                        new HashSet<>(Collections.singletonList(lineItem)), CartStatus.OPEN);
                                lineItem.setCart(cart);
                                cartRepository.save(cart);
                            }
                    );
                },
                () -> { throw new BookstoreException("Adding To Cart Failed: Unable to find book with Id # "+bookId);
                }
        );
    }

    public Cart getCartByCustomerAndCartStatus(long customerId, CartStatus cartStatus){
        Customer customer = customerService.getCustomer(customerId);
        return cartRepository.findByCustomerAndCartStatus(customer, cartStatus)
                .orElseThrow(()-> new BookstoreException("Cannot find cart for customer with customerId "+customerId));
    }
}
