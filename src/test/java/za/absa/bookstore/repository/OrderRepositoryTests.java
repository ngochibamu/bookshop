package za.absa.bookstore.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import za.absa.bookstore.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class OrderRepositoryTests {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testPlaceOrder() {

       BigDecimal totalPrice = BigDecimal.valueOf(1200);
        Book book = new Book(
                "Reactive Systems In Java",
                "Reactive systems and event-driven architecture in Quarkus2",
                "English",
                "YTIGHSGHKAHSA742",
                BigDecimal.valueOf(1000),
                0D,
                460,
                "4 x 5 x 6",
                "Ngonidzashe Chibamu",
                "http://bookstore.ngoni.net/image",
                "Ngonidzashe Chibamu"
        );
        book.setCreatedAt(LocalDateTime.now());
        book.setLastModifiedAt(LocalDateTime.now());

        Book bookEntity = bookRepository.save(book);

        Customer customer = new Customer("ngoni@vip.net",
                "ngoni",
                "chibamu");
        customer.setCreatedAt(LocalDateTime.now());
        customer.setLastModifiedAt(LocalDateTime.now());

        Address shippingAddress = new Address(
                "44 Gucci street",
                "Craigavon",
                "Johannesburg",
                "Gauteng",
                "2192",
                "South Africa",
                "Shipping",
                customer
        );
        shippingAddress.setCreatedAt(LocalDateTime.now());
        shippingAddress.setLastModifiedAt(LocalDateTime.now());
        Address billingAddress = new Address(
                "44 Gucci street",
                "Craigavon",
                "Johannesburg",
                "Gauteng",
                "2192",
                "South Africa",
                "Billing",
                customer
        );
        billingAddress.setCreatedAt(LocalDateTime.now());
        billingAddress.setLastModifiedAt(LocalDateTime.now());

        customer.setAddress(new HashSet<>(List.of(shippingAddress, billingAddress)));
        Customer customerEntity = customerRepository.save(customer);

        LineItem lineItem = new LineItem(2, totalPrice, bookEntity);
        lineItem.setCreatedAt(LocalDateTime.now());
        lineItem.setLastModifiedAt(LocalDateTime.now());
        Cart cart = new Cart(customerEntity, new HashSet<>(Collections.singletonList(lineItem)), CartStatus.OPEN);
        cart.setCreatedAt(LocalDateTime.now());
        cart.setLastModifiedAt(LocalDateTime.now());
        lineItem.setCart(cart);
        Cart cartEntity = cartRepository.save(cart);
        Order order = new Order(cart, totalPrice, LocalDateTime.now(), OrderStatus.PENDING);
        order.setCart(cartEntity);
        order.setCreatedAt(LocalDateTime.now());
        order.setLastModifiedAt(LocalDateTime.now());
        Order orderEntity = orderRepository.save(order);
        assertThat(orderEntity.getId(), is(notNullValue()));
        assertEquals(cartEntity.getId(), orderEntity.getCart().getId());
    }
}
