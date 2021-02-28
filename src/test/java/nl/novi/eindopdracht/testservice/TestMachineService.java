package nl.novi.eindopdracht.testservice;

import nl.novi.eindopdracht.EindopdrachtApplication;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.repository.MachineRepository;
import nl.novi.eindopdracht.service.MachineService;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static nl.novi.eindopdracht.model.EMachine.CONVENTIONEEL;
import static nl.novi.eindopdracht.model.EMachine.ZAAG;

@SpringBootTest
@ContextConfiguration(classes = {EindopdrachtApplication.class})
public class TestMachineService {

    @Autowired
    private MachineService machineService;

    @MockBean
    private MachineRepository machineRepository;

    @Test
    public void testGetMachines(){
        Mockito.when(machineRepository.findAll()).thenReturn(Stream.of(new Machine(1,ZAAG),new Machine(2,CONVENTIONEEL)).collect(Collectors.toList()));
        Assertions.assertEquals(2,machineService.getAllMachines().size());
    }

    @Test
    public void testCreateMachine(){
        Machine machine = new Machine(1, ZAAG);
                Mockito.when(machineRepository.save(machine)).thenReturn(machine);
        Assertions.assertEquals(machine.getMachine(), machineService.createMachine(machine));
    }

    @Test
    public void testDeleteMachine(){
        Machine machine = new Machine(1,ZAAG);
        machineRepository.deleteById(machine.getId());
        Mockito.verify(machineRepository,Mockito.times(1)).deleteById(machine.getId());
    }
}
