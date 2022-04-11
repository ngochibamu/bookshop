package za.absa.bookstore.service.api;

import za.absa.bookstore.model.Cart;
import za.absa.bookstore.model.CartStatus;

public interface CartService {

    void addToCart(long bookId, Integer quantity, long customerId);
    Cart getCartByCustomerAndCartStatus(long customerId, CartStatus cartStatus);
}
