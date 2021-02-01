package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.Employee;
import nl.novi.eindopdracht.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Collection<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
