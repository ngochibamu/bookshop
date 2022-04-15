package za.absa.bookstore.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import za.absa.bookstore.model.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest
public class CartRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void shouldSuccessFullySaveCartAndLineItems(){

        Long bookId = 1L, bookId2 = 2L, customerId = 1L;
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Book bookEntity = bookRepository.findById(bookId).orElse(null);
        Book bookEntity2 = bookRepository.findById(bookId2).orElse(null);

        LineItem lineItem = new LineItem(2, bookEntity.getPrice(), bookEntity);
        lineItem.setCreatedAt(LocalDateTime.now());
        lineItem.setLastModifiedAt(LocalDateTime.now());
        LineItem lineItem2 = new LineItem(1, bookEntity2.getPrice(), bookEntity2);
        lineItem2.setCreatedAt(LocalDateTime.now());
        lineItem2.setLastModifiedAt(LocalDateTime.now());
        LineItem lineItem3 = new LineItem(4, bookEntity2.getPrice(), bookEntity2);
        lineItem3.setCreatedAt(LocalDateTime.now());
        lineItem3.setLastModifiedAt(LocalDateTime.now());
        Cart cart = new Cart(customer, new HashSet<>(List.of(lineItem, lineItem2, lineItem3)), CartStatus.OPEN);
        cart.setCreatedAt(LocalDateTime.now());
        cart.setLastModifiedAt(LocalDateTime.now());
        lineItem.setCart(cart);
        lineItem2.setCart(cart);
        lineItem3.setCart(cart);
        Cart cartEntity = cartRepository.save(cart);
        assertThat(cartEntity.getId(), is(notNullValue()));
        assertThat(cartEntity.getLineItems(), hasSize(3));

    }
}
