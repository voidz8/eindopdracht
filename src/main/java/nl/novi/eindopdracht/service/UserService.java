package nl.novi.eindopdracht.service;

import nl.novi.eindopdracht.model.User;
import nl.novi.eindopdracht.model.Machine;
import nl.novi.eindopdracht.model.Role;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Collection<User> getAllUsers();
    Optional<User> getUser(String username);
    String createUser(User user);
    void deleteUser(String username);
    void updateUser(String username, User user);
    void partialUpdateUser(String username, Map<String, Object> fields);
    Collection<Role> getRoles(String username);
    void addRole(String username, Role role);
    void removeRole(String username);
    Collection<Machine> getMachines(String username);
    void addMachine(String username, Machine machine);
    void removeMachine(String username, Machine machine);
}
