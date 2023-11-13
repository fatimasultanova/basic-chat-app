package model.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(Exception exception) {
        super(exception.getMessage());

    }
}
