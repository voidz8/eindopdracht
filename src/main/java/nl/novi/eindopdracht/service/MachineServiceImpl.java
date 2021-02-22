package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.exceptions.MachineNotFoundException;
import nl.novi.eindopdracht.model.EMachine;
import nl.novi.eindopdracht.model.User;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class MachineServiceImpl implements MachineService{

    @Autowired
    private MachineRepository machineRepository;


    @Override
    public List<Machine> getAllMachines() {
        return machineRepository.findAll();
    }

    @Override
    public Optional<Machine> getMachineById(long id) {
        return machineRepository.findById(id);
    }

    @Override
    public EMachine createMachine(Machine machine) {
        Machine newMachine = machineRepository.save(machine);
        return machine.getMachine();
    }

    @Override
    public void deleteMachine(long id) {
        if (!machineRepository.existsById(id)){throw new MachineNotFoundException();}
        machineRepository.deleteById(id);
    }

    @Override
    public Collection<User> getEmployee(long id) {
        if (!machineRepository.existsById(id)){throw new MachineNotFoundException();}
        Optional<Machine> machine= machineRepository.findById(id);
        return machine.get().getEmployee();
    }
}
