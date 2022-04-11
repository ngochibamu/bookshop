package za.absa.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.absa.bookstore.exception.BookstoreException;
import za.absa.bookstore.model.Cart;
import za.absa.bookstore.model.CartStatus;
import za.absa.bookstore.model.Customer;
import za.absa.bookstore.repository.CartRepository;
import za.absa.bookstore.repository.CustomerRepository;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    public Optional<Cart> findCustomerActiveCart(long customerId) {

        return Optional.of(customerRepository.existsById(customerId))
                .map(customer -> cartRepository.findByCartStatus(CartStatus.OPEN))
                .orElseThrow( () -> new BookstoreException("Cannot find customer with Id "+customerId));
    }

    public Customer getCustomer(long customerId){
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new BookstoreException("Cannot find customer with Id "+customerId));
    }

}
