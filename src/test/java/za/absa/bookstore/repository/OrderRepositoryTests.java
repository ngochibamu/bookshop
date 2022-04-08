package za.absa.bookstore.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import za.absa.bookstore.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

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

        Book bookEntity = bookRepository.save(book);

        Customer customer = new Customer("ngoni@vip.net",
                "ngoni",
                "chibamu", new HashSet<>(),
                new ArrayList<>(),
                null);

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


        Collections.addAll(customer.getAddress(), shippingAddress, billingAddress);
        Customer customerEntity = customerRepository.save(customer);

        Order order = new Order(totalPrice, LocalDateTime.now(), customerEntity);
        LineItem lineItem = new LineItem(2, totalPrice, bookEntity);
        Cart cart = new Cart(customerEntity, new HashSet<>(Collections.singletonList(lineItem)), CartStatus.OPEN);
        lineItem.setCart(cart);
        Cart cartEntity = cartRepository.save(cart);

        order.setCarts(new HashSet<>(Collections.singletonList(cartEntity)));
        Order orderEntity = orderRepository.save(order);
        assertThat(orderEntity.getId(), is(notNullValue()));
    }
}
