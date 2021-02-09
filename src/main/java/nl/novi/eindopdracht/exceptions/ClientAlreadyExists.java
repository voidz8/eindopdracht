package nl.novi.eindopdracht.exceptions;

public class ClientAlreadyExists extends RuntimeException {
    public ClientAlreadyExists(){super("Client already exists.");}
}
