package za.absa.bookstore.dto;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import java.math.BigDecimal;

@Getter
@Builder
public class BookData {

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
    private String author;
}
