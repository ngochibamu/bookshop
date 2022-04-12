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

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/orderlist")
    public List<OrderData> findBookOrders(long customerId){
        return orderService.getOrdersForCustomer(customerId);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
