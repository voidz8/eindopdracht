package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.exceptions.UserNotFoundException;
import nl.novi.eindopdracht.model.User;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Role;
import nl.novi.eindopdracht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(String username) {
    if(!userRepository.existsByUsername(username)) {throw new UserNotFoundException();}
    return userRepository.findByUsername(username);
    }

    @Override
    public String createUser(User user) {
    User newUser = userRepository.save(user);
    return newUser.getUsername();}

    @Override
    public void deleteUser(String username) {
        if(!userRepository.existsByUsername(username)){throw new UserNotFoundException();}
        userRepository.deleteById(username);
    }

    @Override
    public void updateUser(String username, User user) {
    if(!userRepository.existsByUsername(username)){throw new UserNotFoundException();
    }
    User existingUser = userRepository.findByUsername(username).get();
    existingUser.setUsername(user.getUsername());
    existingUser.setPassword(user.getPassword());
    existingUser.setMachines(user.getMachines());
    existingUser.setRoles(user.getRoles());
    userRepository.save(existingUser);
    }

    @Override
    public void partialUpdateUser(String username, String email, String password, Machine machine, Role roles, User employee) {
    if (!userRepository.existsByUsername(username)){throw new UserNotFoundException();}
    User existingUser = userRepository.findByUsername(username).get();
    if (existingUser.getUsername() != null){existingUser.setUsername(employee.getUsername());}
    if (existingUser.getPassword() !=null){existingUser.setPassword(employee.getPassword()); }
    if (existingUser.getMachines() != null){existingUser.setMachines(employee.getMachines());}
    if (existingUser.getRoles() != null){existingUser.setRoles(employee.getRoles());}
    userRepository.save(existingUser);
    }

    @Override
    public Collection<Role> getRoles(String username) {
        if(!userRepository.existsByUsername(username)){throw new UserNotFoundException();}
        Optional<User> employee = userRepository.findByUsername(username);
        return employee.get().getRoles();
    }

    @Override
    public void addRole(String username, Set<Role> roles) {
        if (!userRepository.existsByUsername(username)){throw new UserNotFoundException();}
        User employee = userRepository.findByUsername(username).get();
        employee.setRoles(roles);
        userRepository.save(employee);
    }




}
