package za.absa.bookstore.service.api;

import za.absa.bookstore.dto.CartData;
import za.absa.bookstore.model.Cart;
import za.absa.bookstore.model.CartStatus;

import java.util.List;

public interface CartService {

    void addToCart(long bookId, Integer quantity, long customerId);
    Cart getCartByCustomerAndCartStatus(long customerId, CartStatus cartStatus);
    List<Cart> getCartsForCustomer(long customerId);
    CartData getCartData(long customerId);
}
