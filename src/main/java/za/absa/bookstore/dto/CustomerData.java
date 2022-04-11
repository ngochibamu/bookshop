package za.absa.bookstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerData {

    private String emailAddress;
    private String firstName;
    private String lastName;
    private AddressData address;
}
