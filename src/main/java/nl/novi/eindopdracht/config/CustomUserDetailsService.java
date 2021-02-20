package nl.novi.eindopdracht.config;

import nl.novi.eindopdracht.exceptions.EmployeeNotFoundException;
import nl.novi.eindopdracht.model.Employee;
import nl.novi.eindopdracht.model.Role;
import nl.novi.eindopdracht.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeService employeeService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeService.getEmployeeByEmail(email);
        if(employee == null){throw new EmployeeNotFoundException();
        }
        String password = employee.get().getPassword();

        Set<Role> roles = employee.get().getRoles();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role: roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));
        }
        return new org.springframework.security.core.userdetails.User(email, password, grantedAuthorities);
    }
}
