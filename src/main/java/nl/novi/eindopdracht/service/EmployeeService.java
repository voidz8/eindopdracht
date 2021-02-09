package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.Employee;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Role;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface EmployeeService {
    Collection<Employee> getAllEmployees();
    boolean employeeExists(String name);
    Optional<Employee> getEmployeeByFullName(String name);
    String createEmployee(Employee employee);
    void deleteEmployee(String name);
    void updateEmployee(String name, Employee employee);
    void partialUpdateEmployee(String name, String email, String password, Machine machine, Role roles, Employee employee);
}
