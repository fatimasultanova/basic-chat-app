package model.enums;

public enum Exception {

    CLIENT_NOT_FOUND("Client not found!");

    private final String message;

    Exception(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
