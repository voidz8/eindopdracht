package nl.novi.eindopdracht.exceptions;

public class ClientAlreadyExists extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ClientAlreadyExists(){super("Client already exists.");}
}
