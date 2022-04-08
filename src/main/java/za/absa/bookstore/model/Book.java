package za.absa.bookstore.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Books")
public class Book extends BookstoreData {

    private String title;
    private String description;
    private String language;
    private String ISBN;
    private BigDecimal price;
    private Double weight;
    @Column(name = "number_of_pages")
    private Integer numberOfPages;
    private String dimensions;
    private String publisher;
    @Column(name = "image_url")
    private String imageUrl;
    private String author;
}
