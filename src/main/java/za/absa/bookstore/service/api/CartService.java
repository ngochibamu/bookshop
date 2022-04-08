package za.absa.bookstore.service.api;

public interface CartService {

    void addToCart(long bookId, Integer quantity, long customerId);
}
