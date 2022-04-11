package za.absa.bookstore.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.absa.bookstore.dto.OrderData;
import za.absa.bookstore.model.Book;
import za.absa.bookstore.model.Cart;
import za.absa.bookstore.model.Customer;
import za.absa.bookstore.model.OrderStatus;
import za.absa.bookstore.service.api.CartService;
import za.absa.bookstore.service.api.OrderService;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BookOrderServiceTests {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;

    @Test
    public void shouldSuccessfullySaveOrder(){
        int bookId = 1, quantity = 1, customerId = 1;
        Book book = bookService.getBook(bookId);
        Customer customer = customerService.getCustomer(customerId);
        cartService.addToCart(bookId, quantity, customerId);

        orderService.placeOrder(customerId);
        OrderData orderData = orderService.getOrderForCustomer(customerId, OrderStatus.PENDING);
        assertThat(orderData, is(notNullValue()));
    }
}
