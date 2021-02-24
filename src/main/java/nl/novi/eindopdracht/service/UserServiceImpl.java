package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.exceptions.UserNotFoundException;
import nl.novi.eindopdracht.model.User;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Role;
import nl.novi.eindopdracht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Map;
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
    public void partialUpdateUser(String username, Map<String, Object> fields) {
        if(!userRepository.existsByUsername(username)){throw new UserNotFoundException();}
        User user = userRepository.findByUsername(username).get();
        for (String field : fields.keySet()) {
            switch (field.toLowerCase()) {
                case "email":
                    user.setEmail((String) fields.get(field));
                    break;
                case "password":
                    user.setPassword((String) fields.get(field));
                    break;
            }
        }
        userRepository.save(user);
    }

    @Override
    public Collection<Role> getRoles(String username) {
        if(!userRepository.existsByUsername(username)){throw new UserNotFoundException();}
        User user = userRepository.findByUsername(username).get();
        return user.getRoles();
    }

    @Override
    public void addRole(String username, Role role) {
        if (!userRepository.existsByUsername(username)){throw new UserNotFoundException();}
        User user = userRepository.findByUsername(username).get();
        user.addRole(role);
        userRepository.save(user);
    }

    @Override
    public void removeRole(String username) {
        if (!userRepository.existsByUsername(username)){throw new UserNotFoundException();}
        User user = userRepository.findByUsername(username).get();;
        for (Role role : user.getRoles()){
            user.removeRole(role);
        }
        userRepository.save(user);
    }

    @Override
    public Collection<Machine> getMachines(String username) {
        if (!userRepository.existsByUsername(username)){throw new UserNotFoundException();}
        Optional<User> user = userRepository.findByUsername(username);
        return user.get().getMachines();
    }

    @Override
    public void addMachine(String username, Machine machine) {
    if (!userRepository.existsByUsername(username)){throw new UserNotFoundException();}
    User user = userRepository.findByUsername(username).get();
    user.addMachine(machine);
    userRepository.save(user);
    }

    @Override
    public void removeMachine(String username, Machine machine) {
        if (!userRepository.existsByUsername(username)){throw new UserNotFoundException();}
        User user = userRepository.findByUsername(username).get();
        user.removeMachine(machine);
        //for (Machine machine : user.getMachines()){
          //  machine.getUsers().remove(user);
        //}
        userRepository.save(user);
    }
}
