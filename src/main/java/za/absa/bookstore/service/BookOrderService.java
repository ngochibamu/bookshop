package za.absa.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.absa.bookstore.dto.OrderData;
import za.absa.bookstore.model.*;
import za.absa.bookstore.repository.OrderRepository;
import za.absa.bookstore.service.api.CartService;
import za.absa.bookstore.service.api.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ServiceHelper serviceHelper;

    @Autowired
    public BookOrderService(
            OrderRepository orderRepository,
            CartService cartService,
            ServiceHelper serviceHelper) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.serviceHelper = serviceHelper;
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
    public OrderData getNewOrderForCustomer(long customerId, OrderStatus orderStatus) {

        Cart cart = cartService.getCartByCustomerAndCartStatus(customerId, CartStatus.CLOSED);
        Order order = cart.getOrder();
        return OrderData.builder()
                .orderId(order.getId())
                .totalPrice(order.getTotalPrice())
                .orderDate(order.getOrderDate())
                .orderStatus(orderStatus)
                .lineItems(serviceHelper.LineItemToLineItemData(cart))
                .build();
    }

    @Override
    public List<OrderData> getOrdersForCustomer(long customerId) {
        List<OrderData> orders = new ArrayList<>();
        cartService.getCartsForCustomer(customerId)
                .forEach( (cart) -> orders.add(OrderData.builder()
                        .orderId(cart.getOrder().getId())
                        .totalPrice(cart.getOrder().getTotalPrice())
                        .orderStatus(cart.getOrder().getOrderStatus())
                        .orderDate(cart.getOrder().getOrderDate())
                        .lineItems(serviceHelper.LineItemToLineItemData(cart))
                        .build()));
        return orders;
    }
}
