package nl.novi.eindopdracht.model;

import java.util.Set;

public class WorkshopWorker extends Employee{
    public WorkshopWorker(String name, String email, String password, Set<Machine> machines) {
        super(name, email, password, machines);
    }
}
