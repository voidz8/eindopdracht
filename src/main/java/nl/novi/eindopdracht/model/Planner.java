package nl.novi.eindopdracht.model;

import java.util.Set;

public class Planner extends Employee{
    public Planner(String name, String email, String password, Set<Machine> machines) {
        super(name, email, password, machines);
    }
}
