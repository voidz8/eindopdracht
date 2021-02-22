package nl.novi.eindopdracht.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Can not find specified user.");
    }
}
