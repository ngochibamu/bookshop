package za.absa.bookstore.exception;

public class BookstoreBadRequest extends RuntimeException {

    public BookstoreBadRequest(String message) {
        super(message);
    }
}
