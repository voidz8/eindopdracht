package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.EMachine;
import nl.novi.eindopdracht.model.User;
import nl.novi.eindopdracht.model.Machine;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MachineService {
    List<Machine> getAllMachines();
    Optional<Machine> getMachineById(long id);
    EMachine createMachine(Machine machine);
    void deleteMachine(long id);
    Collection<User> getEmployee(long id);
}
