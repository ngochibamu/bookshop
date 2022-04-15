package za.absa.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import za.absa.bookstore.dto.OrderData;
import za.absa.bookstore.service.api.OrderService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/orderlist",  produces = APPLICATION_JSON_VALUE)
    public List<OrderData> findBookOrders(long customerId){
        return orderService.getOrdersForCustomer(customerId);
    }

    @PostMapping(value = "/placeOrder",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> placeOrder(long customerId){
        orderService.placeOrder(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
