package nl.novi.eindopdracht.exceptions;

import nl.novi.eindopdracht.model.Machine;

public class MachineNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public MachineNotFoundException(){super("Can not find specified machine.");}
}
