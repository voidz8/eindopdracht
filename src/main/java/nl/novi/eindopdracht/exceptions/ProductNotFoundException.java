package nl.novi.eindopdracht.exceptions;

public class ProductNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public ProductNotFoundException(){super("Can not find specified product.");}
}
