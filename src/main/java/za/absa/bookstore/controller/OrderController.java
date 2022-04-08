package za.absa.bookstore.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import za.absa.bookstore.dto.BookOrder;

import java.util.List;

@RestController
public class OrderController {

    public List<BookOrder> findBookOrders(){
        return null;
    }

    @PostMapping("/placeOrder")
    public void placeOrder(){

    }

}
