package jpa.jpa_shop.exception;

public class NoEntity extends IllegalStateException {
    public NoEntity() {
        super();
    }

    public NoEntity(String s) {
        super(s);
    }

    public NoEntity(String message, Throwable cause) {
        super(message, cause);
    }
}
