package za.absa.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.absa.bookstore.dto.BookData;
import za.absa.bookstore.dto.LineItemData;
import za.absa.bookstore.dto.OrderData;
import za.absa.bookstore.model.*;
import za.absa.bookstore.repository.OrderRepository;
import za.absa.bookstore.service.api.CartService;
import za.absa.bookstore.service.api.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class BookOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final CustomerService customerService;

    @Autowired
    public BookOrderService(
            OrderRepository orderRepository,
            CartService cartService,
            CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.customerService = customerService;
    }

    @Override
    public void placeOrder(long customerId){
        Cart cart = cartService.getCartByCustomerAndCartStatus(customerId, CartStatus.OPEN);
        BigDecimal orderTotalPrice = cart
                .getLineItems()
                .stream()
                .map(LineItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Order order = new Order(cart, orderTotalPrice, LocalDateTime.now(), OrderStatus.PENDING);
        cart.setCartStatus(CartStatus.CLOSED);
        order.setCart(cart);
        orderRepository.save(order);
    }

    @Override
    public OrderData getOrderForCustomer(long customerId, OrderStatus orderStatus) {

        Cart cart = cartService.getCartByCustomerAndCartStatus(customerId, CartStatus.CLOSED);
        Order order = cart.getOrder();
        return OrderData.builder()
                .orderId(order.getId())
                .totalPrice(order.getTotalPrice())
                .orderDate(order.getOrderDate())
                .orderStatus(orderStatus)
                .lineItems(convertToItemData(cart))
                .build();
    }

    private BookData convertBookToBookData(Book book){
        return BookData.builder()
                .author(book.getAuthor())
                .description(book.getDescription())
                .title(book.getTitle())
                .ISBN(book.getISBN())
                .numberOfPages(book.getNumberOfPages())
                .build();
    }

    private Set<LineItemData> convertToItemData(Cart cart){
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
