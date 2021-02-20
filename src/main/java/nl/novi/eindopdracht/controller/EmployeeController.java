package nl.novi.eindopdracht.controller;

import nl.novi.eindopdracht.model.Employee;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Role;
import nl.novi.eindopdracht.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;


@RestController
public class EmployeeController implements Serializable {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/employees")
    public ResponseEntity<Object> getAllEmployees(){
        Collection<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping(value = "/employees/{email}")
    public ResponseEntity<Object> getEmployeeByName(@PathVariable(value = "email") String email){
        return new ResponseEntity<>(employeeService.getEmployeeByEmail(email), HttpStatus.OK);
    }
    @PostMapping(value = "/employees")
    public ResponseEntity<Object> createEmployee(@RequestBody Employee employee){
        String newEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>("A new employee is created: "+newEmployee+".", HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/employees/{email}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable(value = "email") String email){
        employeeService.deleteEmployee(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping(value = "/employees/{email}")
    public ResponseEntity<Object> updateEmployee(@PathVariable(value = "email") String email, @RequestBody Employee employee){
        employeeService.updateEmployee(email,employee);
        return new ResponseEntity<>("Employee with email: "+email+ " is updated.", HttpStatus.OK);
    }
    @PatchMapping(value = "/employees/{email}")
    public ResponseEntity<Object> updateEmployeePartial(@PathVariable(value = "email")@RequestBody Employee employee,String email, String password, Machine machine, Role roles, String name) {
        employeeService.partialUpdateEmployee(name, email, password, machine, roles, employee);
        return new ResponseEntity<>("Employee with email: " + email + " is updated.", HttpStatus.OK);
    }
    @GetMapping(value = "/employees/{email}/roles")
    public ResponseEntity<Object> getRoles(@PathVariable(value = "email") String email){
        Collection<Role> roles = employeeService.getRoles(email);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
    @PostMapping(value = "/employees/{email}/roles")
    public ResponseEntity<Object> addRoles(@PathVariable(value = "email") String email, Set<Role> roles){
    employeeService.addRole(email,roles);
    return new ResponseEntity<>("Successfully added "+roles+ " to "+email, HttpStatus.OK);
    }

}
