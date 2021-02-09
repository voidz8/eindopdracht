package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.exceptions.EmployeeAlreadyExists;
import nl.novi.eindopdracht.exceptions.EmployeeNotFoundException;
import nl.novi.eindopdracht.model.Employee;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Role;
import nl.novi.eindopdracht.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Collection<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeByFullName(String name) {
    if(!employeeRepository.existsByName(name)) {throw new EmployeeNotFoundException();}
    return employeeRepository.findByName(name);
    }

    @Override
    public String createEmployee(Employee employee) {
    Employee newEmployee = employeeRepository.save(employee);
    if (employeeRepository.existsByName(newEmployee.getName())){throw new EmployeeAlreadyExists();}
    return newEmployee.getName();}

    @Override
    public void deleteEmployee(String name) {
        if(!employeeRepository.existsByName(name)){throw new EmployeeNotFoundException();}
        employeeRepository.deleteById(name);
    }

    @Override
    public void updateEmployee(String name, Employee employee) {
    if(!employeeRepository.existsByName(name)){throw new EmployeeNotFoundException();}
    Employee existingEmployee = employeeRepository.findByName(name).orElse(null);
    existingEmployee.setName(employee.getName());
    existingEmployee.setEmail(employee.getName());
    existingEmployee.setPassword(employee.getPassword());
    existingEmployee.setMachines(employee.getMachines());
    existingEmployee.setRoles(employee.getRoles());
    employeeRepository.save(existingEmployee);
    }

    @Override
    public void partialUpdateEmployee(String name, String email, String password, Machine machine, Role roles, Employee employee) {
    if (!employeeRepository.existsByName(name)){throw new EmployeeNotFoundException();}
    Employee existingEmployee = employeeRepository.findByName(name).orElse(null);
    if (existingEmployee.getName() != null){existingEmployee.setName(employee.getName());}
    if (existingEmployee.getEmail() != null){existingEmployee.setEmail(employee.getEmail());}
    if (existingEmployee.getPassword() !=null){existingEmployee.setPassword(employee.getPassword()); }
    if (existingEmployee.getMachines() != null){existingEmployee.setMachines(employee.getMachines());}
    if (existingEmployee.getRoles() != null){existingEmployee.setRoles(employee.getRoles());}
    employeeRepository.save(existingEmployee);
    }

    @Override
    public boolean employeeExists(String name) {
        return employeeRepository.existsById(name);
    }



}
