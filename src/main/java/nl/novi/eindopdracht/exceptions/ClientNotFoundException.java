package nl.novi.eindopdracht.exceptions;

public class ClientNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ClientNotFoundException(){super("Can not find specified client.");}
}
