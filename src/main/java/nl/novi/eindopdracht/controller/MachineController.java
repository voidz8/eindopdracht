package nl.novi.eindopdracht.controller;

import nl.novi.eindopdracht.model.EMachine;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MachineController {

    @Autowired
    private MachineService machineService;

    @GetMapping(value = "/machines")
    public ResponseEntity<Object> getAllMachines(){
        List<Machine> machines = machineService.getAllMachines();
        return new ResponseEntity<>(machines, HttpStatus.OK);
    }
    @GetMapping(value = "/machines/{id}")
    public ResponseEntity<Object> getMachine(@PathVariable(value = "id") long id){
        return new ResponseEntity<>(machineService.getMachineById(id), HttpStatus.OK);
    }

   @PostMapping(value = "/machines")
   public ResponseEntity<Object> createMachine(@RequestBody Machine machine){
        EMachine newMachine = machineService.createMachine(machine);
        return new ResponseEntity<>("A new machine is created " + machine, HttpStatus.CREATED);
   }
   @DeleteMapping(value = "machines/{id}")
    public ResponseEntity<Object> deleteMachine(@PathVariable(value = "id") long id){
        machineService.deleteMachine(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }


}
