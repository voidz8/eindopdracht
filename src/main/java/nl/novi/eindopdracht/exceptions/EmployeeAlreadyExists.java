package nl.novi.eindopdracht.exceptions;

public class EmployeeAlreadyExists extends RuntimeException {
    public EmployeeAlreadyExists(){super("Client already exists.");}
}
