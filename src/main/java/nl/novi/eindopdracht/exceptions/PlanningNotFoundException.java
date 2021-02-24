package nl.novi.eindopdracht.exceptions;

public class PlanningNotFoundException extends RuntimeException{
    public PlanningNotFoundException(){super("Can not find planning.");}
}
