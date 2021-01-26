package nl.novi.eindopdracht.model;

import java.util.Set;

public class MachineProgrammer extends Employee{
    public MachineProgrammer(String name, String email, String password, Set<Machine> machines) {
        super(name, email, password, machines);
    }
}
