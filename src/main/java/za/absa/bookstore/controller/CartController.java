package za.absa.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import za.absa.bookstore.dto.AddToCartRequest;
import za.absa.bookstore.exception.BookstoreBadRequest;
import za.absa.bookstore.service.api.CartService;

import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(value = "/addToCart", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addToCart(@RequestBody AddToCartRequest addToCartRequest){

        if(Objects.isNull(addToCartRequest)){
            throw new BookstoreBadRequest("AddToCart requires number values for bookId, quantity and customerId");
        }
        cartService.addToCart(addToCartRequest.getBookId(), addToCartRequest.getQuantity(), addToCartRequest.getCustomerId());
        return new ResponseEntity<>("Book successfully added to cart", HttpStatus.OK);
    }
}
