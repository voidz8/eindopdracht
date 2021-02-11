package nl.novi.eindopdracht.exceptions;

public class ProductAlreadyExists extends RuntimeException {
    public ProductAlreadyExists(){super("Product already exists.");}
}
