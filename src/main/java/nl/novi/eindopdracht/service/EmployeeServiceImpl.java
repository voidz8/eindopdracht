package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.exceptions.EmployeeNotFoundException;
import nl.novi.eindopdracht.model.Employee;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Role;
import nl.novi.eindopdracht.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
    public Optional<Employee> getEmployeeByEmail(String email) {
    if(!employeeRepository.existsByEmail(email)) {throw new EmployeeNotFoundException();}
    return employeeRepository.findByEmail(email);
    }

    @Override
    public String createEmployee(Employee employee) {
    Employee newEmployee = employeeRepository.save(employee);
    return newEmployee.getName();}

    @Override
    public void deleteEmployee(String email) {
        if(!employeeRepository.existsByEmail(email)){throw new EmployeeNotFoundException();}
        employeeRepository.deleteById(email);
    }

    @Override
    public void updateEmployee(String email, Employee employee) {
    if(!employeeRepository.existsByEmail(email)){throw new EmployeeNotFoundException();}
    Employee existingEmployee = employeeRepository.findByEmail(email).orElse(null);
    existingEmployee.setName(employee.getName());
    existingEmployee.setPassword(employee.getPassword());
    existingEmployee.setMachines(employee.getMachines());
    existingEmployee.setRoles(employee.getRoles());
    employeeRepository.save(existingEmployee);
    }

    @Override
    public void partialUpdateEmployee(String name, String email, String password, Machine machine, Role roles, Employee employee) {
    if (!employeeRepository.existsByEmail(email)){throw new EmployeeNotFoundException();}
    Employee existingEmployee = employeeRepository.findByEmail(email).orElse(null);
    if (existingEmployee.getName() != null){existingEmployee.setName(employee.getName());}
    if (existingEmployee.getPassword() !=null){existingEmployee.setPassword(employee.getPassword()); }
    if (existingEmployee.getMachines() != null){existingEmployee.setMachines(employee.getMachines());}
    if (existingEmployee.getRoles() != null){existingEmployee.setRoles(employee.getRoles());}
    employeeRepository.save(existingEmployee);
    }

    @Override
    public Collection<Role> getRoles(String email) {
        if(!employeeRepository.existsByEmail(email)){throw new EmployeeNotFoundException();}
        Optional<Employee> employee = employeeRepository.findByEmail(email);
        return employee.get().getRoles();
    }

    @Override
    public void addRole(String email, Set<Role> roles) {
        if (!employeeRepository.existsByEmail(email)){throw new EmployeeNotFoundException();}
        Employee employee = employeeRepository.findByEmail(email).orElse(null);
        employee.setRoles(roles);
        employeeRepository.save(employee);
    }

    @Override
    public boolean employeeExists(String name) {
        return employeeRepository.existsById(name);
    }



}
