package nl.novi.eindopdracht.exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(){super("Can not find specified client.");}
}
