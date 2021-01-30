package nl.novi.eindopdracht.exceptions;

import nl.novi.eindopdracht.model.Order;

public class OrderNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public OrderNotFoundException(){super("Can not find specified order.");}
}
