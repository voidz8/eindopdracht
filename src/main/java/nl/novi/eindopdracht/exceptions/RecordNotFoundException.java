package nl.novi.eindopdracht.exceptions;

public class RecordNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public RecordNotFoundException(){super("Can not find specified record.");}
}
