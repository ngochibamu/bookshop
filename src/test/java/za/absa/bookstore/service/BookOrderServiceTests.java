package za.absa.bookstore.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import za.absa.bookstore.dto.OrderData;
import za.absa.bookstore.model.OrderStatus;
import za.absa.bookstore.service.api.CartService;
import za.absa.bookstore.service.api.OrderService;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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
        cartService.addToCart(bookId, quantity, customerId);

        orderService.placeOrder(customerId);
        OrderData orderData = orderService.getNewOrderForCustomer(customerId, OrderStatus.PENDING);
        assertThat(orderData, is(notNullValue()));
        assertThat(orderData.getLineItems().size(), is(1));
    }

    @Test
    public void shouldRetriveListOfOrdersForCustomer(){
        int bookId1 = 1, quantity1 = 1, customerId = 1;
        int bookId2 = 3, quantity2 = 2;
        cartService.addToCart(bookId1, quantity1, customerId);
        orderService.placeOrder(customerId);

        cartService.addToCart(bookId2, quantity2, customerId);
        orderService.placeOrder(customerId);

        List<OrderData> orders = orderService.getOrdersForCustomer(customerId);
        assertThat(orders, is(notNullValue()));
        assertThat(orders, hasSize(2));
    }
}
