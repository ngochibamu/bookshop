package za.absa.bookstore.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class CustomerData {

    private String emailAddress;
    private String firstName;
    private String lastName;
    private Set<AddressData> address;
}
