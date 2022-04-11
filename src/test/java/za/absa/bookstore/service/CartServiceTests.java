package za.absa.bookstore.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.absa.bookstore.model.Cart;
import za.absa.bookstore.service.api.CartService;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CartServiceTests {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    @Test
    public void shouldSuccessFullyAddItemToCart(){

        int bookId = 1, quantity = 1, customerId = 1;

        cartService.addToCart(bookId, quantity, customerId);

        Optional<Cart> cart = customerService.findCustomerActiveCart(customerId);

        assertThat(cart, is(notNullValue()));
        assertThat(cart.stream().findFirst().orElse(null), is(notNullValue()));
        assertThat(cart.stream().findFirst().orElse(null).getLineItems(), hasSize(1));

    }


   @Test
    public void shouldSuccessfulyAddItemToExistingCart(){

        int bookId = 1, quantity = 1, customerId = 1;
        int bookId2 = 2, quantity2 = 2;

        cartService.addToCart(bookId, quantity, customerId);
        cartService.addToCart(bookId2, quantity2, customerId);
        Optional<Cart> cart = customerService.findCustomerActiveCart(customerId);

        assertThat(cart, is(notNullValue()));
        assertThat(cart.stream().findFirst().orElse(null), is(notNullValue()));
        assertThat(cart.stream().findFirst().orElse(null).getLineItems(), hasSize(2));
    }
}