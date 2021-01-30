package nl.novi.eindopdracht.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public EmployeeNotFoundException(){super("Can not find specified employee");}
}
