package jpa.jpa_shop.exception;

public class NotSearchId extends RuntimeException{
    public NotSearchId() {
        super();
    }

    public NotSearchId(String message) {
        super(message);
    }

    public NotSearchId(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSearchId(Throwable cause) {
        super(cause);
    }
}
