package za.absa.bookstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Books")
public class Book extends AbstractEntity {

    private String title;
    private String description;
    private String language;
    private String ISBN;
    private BigDecimal price;
    private Double weight;
    private Integer numberOfPages;
    private String dimensions;
    private String publisher;
    private String imageUrl;
}
